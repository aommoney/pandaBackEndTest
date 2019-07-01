package com.demo;

import com.demo.error.ProductNotFoundException;
import com.demo.error.ProductUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class ProductController {
    @Autowired
    private ProductRepository repository;

    // Find
    @GetMapping("/products")
    List<Product> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    Product newProduct(@Valid @RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    // Find
    @GetMapping("/products/{id}")
    Product findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


    // Save or update
    @PutMapping("/products/{id}")
    Product saveOrUpdate(@RequestBody Product newProduct, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newProduct.getName());
                    x.setDesc(newProduct.getDesc());
                    x.setPrice(newProduct.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    // update description only
    @PatchMapping("/products/{id}")
    Product patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String desc = update.get("desc");
                    if (!StringUtils.isEmpty(desc)) {
                        x.setDesc(desc);

                        return repository.save(x);
                    } else {
                        throw new ProductUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new ProductNotFoundException(id);
                });

    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

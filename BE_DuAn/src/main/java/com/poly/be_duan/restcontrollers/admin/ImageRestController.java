package com.poly.be_duan.restcontrollers.admin;

import com.poly.be_duan.entities.Image;
import com.poly.be_duan.entities.Product;
import com.poly.be_duan.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/image")
public class ImageRestController {
    @Autowired
    private ImageService imageService;

    @GetMapping()
    public ResponseEntity<List<Image>> getAll() {
        // sử dụng phương thức findAll bên service
        //
        return ResponseEntity.ok(imageService.findAll());
    }
    @PostMapping("/images")
    public ResponseEntity<Image> create(@Valid @RequestBody Image request) {
        try {
            String fileName = request.getFile().getOriginalFilename();
            String filePath = "/path/to/image/directory/" + fileName; // Đường dẫn tới thư mục lưu trữ ảnh

            // Lưu trữ ảnh vào thư mục
            File destination = new File(filePath);
            request.getFile().transferTo(destination);

            // Tạo đối tượng Image và lưu thông tin đường dẫn ảnh vào nó
            Image image = new Image();
            image.setUrlimage(filePath);

            // Lưu đối tượng Image vào cơ sở dữ liệu hoặc thực hiện các thao tác khác cần thiết
            // ...

            return ResponseEntity.ok(image);
        } catch (IOException e) {
            // Xử lý lỗi khi lưu trữ ảnh
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("store-list-image")
    public ResponseEntity<List<Image>> createListImage(@Valid @RequestBody List<Image> images) {
        try {
            return ResponseEntity.ok(imageService.createList(images));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Image> getOne(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(imageService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @GetMapping("find-by-product/{idProduct}")
    public ResponseEntity<List<Image>> findByProduct(@PathVariable("idProduct") Product product) {
        try {
            return ResponseEntity.ok(imageService.findByProduct(product));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Image> delete(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(imageService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

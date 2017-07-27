package com.karoljanowski.controller;

import com.karoljanowski.domain.Book;
import com.karoljanowski.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-26.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    public static final String BOOK_IMAGE_LOCATION = "/usr/local/opt/bookstoreadmin/book/";

    @Autowired
    private BookService bookService;

    @RequestMapping("/new")
    public String bookAdd(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    public String bookAddPost(@ModelAttribute("book") Book book, HttpServletRequest request){
//        bookService.save(book);
//
//        MultipartFile bookImage = book.getBookImage();
//
//        try {
//            byte[] bytes = bookImage.getBytes();
//            String name = book.getId() + ".png";
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(System.getProperty("bookImagePath")+name)));
////            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+name)));
//            stream.write(bytes);
//            stream.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return "redirect:bookList";
//    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String bookAddPost(@ModelAttribute("book") Book book, HttpServletRequest request) throws IOException{
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();

        if (!bookImage.isEmpty()) {
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(bookImage.getBytes()));
            File destination = new File(BOOK_IMAGE_LOCATION+book.getId()+".png"); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
            ImageIO.write(src, "png", destination);
            //Save the id you have used to create the file name in the DB. You can retrieve the image in future with the ID.
        }

        return "redirect:bookList";
    }

    @RequestMapping("/bookList")
    public String bookList(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        return "bookList";
    }

    @RequestMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id, Model model){
        Book book = bookService.findOne(id);


        model.addAttribute("book", book);
        return "bookInfo";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model){
        Book book =bookService.findOne(id);
        bookService.remove(book);
        //IMAGES ARE NOT DELETED

        return "redirect:bookList";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("id") Long id, Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "updateBook";
    }


    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) throws IOException{
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();

//        if (!bookImage.isEmpty()) {
//            try {
//                byte[] bytes = bookImage.getBytes();
//                String name = book.getId() + ".png";
//
//                Files.delete(Paths.get(System.getProperty("bookImagePath") + name));
//
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(System.getProperty("bookImagePath") + name)));
//                stream.write(bytes);
//                stream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        if (!bookImage.isEmpty()) {

            Files.deleteIfExists(Paths.get(BOOK_IMAGE_LOCATION+book.getId()+".png"));
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(bookImage.getBytes()));
            File destination = new File(BOOK_IMAGE_LOCATION+book.getId()+".png"); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
            ImageIO.write(src, "png", destination);
            //Save the id you have used to create the file name in the DB. You can retrieve the image in future with the ID.
        }
        return "redirect:/book/bookInfo?id=" + book.getId();
    }

}




















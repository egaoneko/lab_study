package net.study.controller;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import net.study.domain.Book;
import net.study.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/15/15 | 2:32 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping("/book")
@PropertySource("classpath:openApi.properties")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Value("${naver.key}")
    private String key;

    @Value("${naver.searchUrl}")
    private String searchUrl;

    @Value("${naver.book}")
    private String target;

    @RequestMapping("/search")
    public String bookSearch(){
        LOGGER.debug("Getting book search page");
        return "book/search";
    }

    @Autowired
    private BookRepository bookRepository;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JSONObject bookQuery(@RequestParam(value = "query", required = true)String query){
        LOGGER.debug("Getting book request for query={}", query);

        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";

        /*
        Searching book by naver open api
         */
        try {
            url = new URL(String.format("%s?query=%s&target=%s&key=%s", searchUrl, query, target, key));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        Convert xml to json
         */
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSONObject jsonObject = (JSONObject) xmlSerializer.read(result);

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Book bookInsert(@RequestParam(value = "image", required = true) String image,
                           @RequestParam(value = "title", required = true) String title,
                           @RequestParam(value = "author", required = true) String author,
                           @RequestParam(value = "publisher", required = true) String publisher,
                           @RequestParam(value = "pubdate", required = true) String pubdate,
                           @RequestParam(value = "price", required = true) Integer price,
                           @RequestParam(value = "isbn", required = true) String isbn) throws Exception{

        Book book = bookRepository.findOneByIsbn(isbn);

        if(book == null){
            book = new Book();
            book.setImage(image);
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublisher(publisher);

            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = format.parse(pubdate);
            book.setPubdate(date);
            book.setPrice(price);
            book.setIsbn(isbn);

            book = bookRepository.save(book);
        }

        return book;
    }
}

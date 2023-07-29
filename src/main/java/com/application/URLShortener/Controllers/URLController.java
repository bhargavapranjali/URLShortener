package com.application.URLShortener.Controllers;

import com.application.URLShortener.Database.UserDatabase.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;


@RestController
public class URLController {

    @Autowired
    private UtilityClass utilityClass;

    @ResponseBody
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody ObjectNode objectNode)
    {
        String userName = objectNode.get("userName").asText();
        String email = objectNode.path("email").asText();
        User user = utilityClass.createUser(userName,email);
        return "User created successfully";
    }

    @RequestMapping(value="/{id}/createUrl", method = RequestMethod.POST)
    @ResponseBody
    public String createUrl(@RequestBody ObjectNode objectNode, @PathVariable int id)
    {
        String originalUrl = objectNode.get("originalUrl").asText();
        ObjectMapper mapper = new ObjectMapper();
        Date exprirationDate = mapper.convertValue(objectNode.get("expirationDate"), Date.class);
        String shortenedUrl = utilityClass.createUrl(originalUrl,exprirationDate,id);
        return shortenedUrl;
    }

    @RequestMapping(value="/deleteUrl", method = RequestMethod.DELETE)
    public void deleteUrl(@RequestBody ObjectNode objectNode)
    {
        String tinyUrl = objectNode.get("tinyUrl").asText();
        utilityClass.deleteUrlById(tinyUrl);
    }
    @GetMapping("/redirect/{tinyUrl}")
    public RedirectView redirectToUrl(@PathVariable String tinyUrl)
    {
        String originalUrl = utilityClass.getOriginalUrlFromDB(tinyUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl);
        return redirectView;
    }
}

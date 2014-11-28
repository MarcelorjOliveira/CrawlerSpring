/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.model;

import java.sql.Date;

/**
 *
 * @author marcelooliveira
 */
public class Document {
        private String id;
    private Date date;
    private String link;
    private String source;
    private String author;
    private String title;
    private String content;
    
    public String getId(){
        return id;
    }
    
    public void setId(String value){
        this.id = value;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date value){
        this.date = value;
    }
    
    public  String getLink(){
        return link;
    } 
    
    public void setLink(String value){
        this.link = value;
    }
    
    public String getSource(){
        return source;
    }
    
    public void setSource(String value){
            this.source = value;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public void setAuthor(String value){
        this.author = value;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String value){
        this.title = value;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String value){
        this.content = value;
    }
}

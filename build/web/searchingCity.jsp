<%-- 
    Document   : Searching
    Created on : Jul 30, 2018, 2:47:52 PM
    Author     : zakhar
--%>

<%@page import="mypack.MyBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <p align ="center">
           <a href="index.xhtml">home</a>  
        </p>
        <form action="SearchingCity">
            <input type="text" name="iname" value="" />
            
            <input type="submit" value="Search" name="" id="but" />
            
            <select name="orcity" size="1" id="sel">
                <option value="CITY">City</option>
                <option value="COUNTRY">Coutry</option>
            </select> 
            
            
        </form>
        
        
</body>
</html>

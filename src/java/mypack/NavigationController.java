/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author zakhar
 */
@ManagedBean(eager = true)
@SessionScoped
public class NavigationController implements Serializable {

    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    public NavigationController() {
    }

    public String showPage() {
        if (pageId == null) {
            return "index.xhtml";
        }

        if (pageId.equals("showData")) {
            return "showData.jsp";
        } else if (pageId.equals("search")) {
            return "Searching.jsp";

        } else if (pageId.equals("addingActor")) {
            return "addingActor";
        } else {
            return "index";
        }
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}

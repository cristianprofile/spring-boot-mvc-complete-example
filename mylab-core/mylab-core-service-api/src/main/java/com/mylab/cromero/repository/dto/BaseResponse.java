package com.mylab.cromero.repository.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <h1>BaseResponse</h1>
 * BaseRequest dto request
 * <p>
 * <b>BaseResponse</b> BaseResponse data transfer object
 * for sevice layer
 *
 * @author Cristian Romero Matesanz
 */
@XmlRootElement
public class BaseResponse {

    private String name;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseResponse [name=");
        builder.append(name);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }


}

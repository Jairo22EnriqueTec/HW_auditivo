package org.helloworld.auditivo;

/**
 * Created by jj on 19/12/18.
 */

public class Adaptador_Senas_Quiz {
    private int img;
    private String text;

    public Adaptador_Senas_Quiz(String text, int  img){

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Adaptador_Senas_Quiz(int img, String text) {
        this.img = img;
        this.text = text;
    }
}

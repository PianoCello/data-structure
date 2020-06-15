package com.hzu.graph;

/** 图的顶点
 * @author PianoCello
 * @date 2019-09-01
 */
public class Vertex {

    //顶点的值
    private String value;
    //遍历时标识是否被访问过
    public boolean visited;

    public Vertex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

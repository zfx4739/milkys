package com.example.SecurityDemo.domain;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SecurityDemo.service.productService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/1 11:30
 * @description：
 * @modified By：
 * @version: $
 */

public class ProgNode {
    @Autowired
    private productService productservice;
    private String id;

    public String getLevel() {
        return level;
    }
    private List<ProgNode> progs;

    public List<ProgNode> getProgs() {
        return progs;
    }

    public void setProgs(List<ProgNode> progs) {
        this.progs = progs;
    }

    public void setLevel(String level) {
        this.level = "3";
    }

    private String name;
    private String level;
    public ProgNode() {
    }

    public ProgNode(String id, String name, String level) {
        super();
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List getChild(){
        //。。。。。。。部分代码省略
//        String prog_id = "**";
//        String prog_name = "***";
//        String sql = " *****";  //这里的sql根据父级id查找相关的子属性
        QueryWrapper<Product> que=new QueryWrapper<Product>();
        que.eq("parents",0);
        List<Product> PList=productservice.list(que);
        List<ProgNode> progNodes= new ArrayList<ProgNode>();
        if(PList.size()>0){
            for (int i = 0;i < PList.size();i++){
                ProgNode node = new ProgNode();
                String progId = PList.get(i).getId().toString();
                String progName = PList.get(i).getName();
                if(progId != null && progId != ""){
                    node.setId(progId);
                    node.setName(progName);
                    progNodes.add(node);
                }
            }
        }
        return progNodes;   //返回一个集合
    }
}
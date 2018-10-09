package com.example.utils;

import java.util.ArrayList;
import java.util.List;

public class PagingUtils {

    /**
     *List分页处理
     * @param list
     * @param pageIndex
     * @param pageSize
     * @param <F>
     * @return
     */
    public static <F> List<F> pagingHandle(List<F> list, int pageIndex, int pageSize){
        if(list == null){//当传入过来的list集合为null时，先进行实例化
            list = new ArrayList<F>();
        }
        if((Object)pageIndex == null){//当传入过来的pageNo为null时，先进行赋值操作
            pageIndex = 1;
        }
        if((Object)pageIndex == null){//当传入过来的dataSize为null时，先进行赋值操作
            pageIndex = 1;
        }
        if(pageIndex <= 0){
            pageIndex = 1;
        }
        //记录一下数据一共有多少条
        int totalitems = list.size();
        //实例化一个接受分页处理之后的数据
        List<F> afterList = new ArrayList<F>();
        for(int i = (pageIndex-1)*pageSize;i < (((pageIndex -1)*pageSize) + pageSize > totalitems ? totalitems:((pageIndex -1)*pageSize) +pageSize);i++) {
            //然后将数据存入afterList中
            afterList.add(list.get(i));
        }
        //然后将处理后的数据集合进行返回
        return afterList;
    }
}

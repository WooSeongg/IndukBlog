package iducs.jsp.final201712070.util;

import iducs.jsp.final201712070.model.Blog;

import java.util.Comparator;

public class DescByBlogTitle implements Comparator<Blog> {

    @Override
    public int compare(Blog o1, Blog o2) {
        return o2.getTitle().compareTo(o1.getTitle()); // o2 <= o1 일경우 음수 / o2 > o1 양수
        //return o1.getTitle().compareTo(o2.getTitle()); 오름차순
    }
}

package iducs.jsp.final201712070.util;

import iducs.jsp.final201712070.model.Blog;
import iducs.jsp.final201712070.model.Blogger;

import java.util.Comparator;

public class DescByBloggerEmail implements Comparator<Blogger> {

    @Override
    public int compare(Blogger o1, Blogger o2) {
        return o2.getEmail().compareTo(o1.getEmail()); // o2 <= o1 일경우 음수 / o2 > o1 양수
        //return o1.getTitle().compareTo(o2.getTitle()); 오름?내림?
    }

}

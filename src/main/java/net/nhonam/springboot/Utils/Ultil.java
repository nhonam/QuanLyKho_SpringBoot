package net.nhonam.springboot.Utils;

import net.nhonam.springboot.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ultil {
//    public static <T> Page<T> convert(List<T> list, Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<T> pageList;
//
//        if (list.size() < startItem) {
//            pageList = List.of();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, list.size());
//            pageList = list.subList(startItem, toIndex);
//        }
//
//        return new PageImpl<>(pageList, pageable, list.size());
//    }

    public static <T> Page<T> toPage(List<T> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int)pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ?
                list.size() :
                pageable.getOffset() + pageable.getPageSize());
        List<T> subList = list.subList(startIndex, endIndex);


        return new PageImpl(subList, pageable, list.size());
    }

    public static <T> Map<String, Object> ListToPage(List<T> list, int page, int size) {
        Pageable paging = PageRequest.of(page, size);


        Page<T> pagelist = Ultil.toPage(list, paging);
        list = pagelist.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("listData", list);
        response.put("currentPage", pagelist.getNumber());
        response.put("totalItems", pagelist.getTotalElements());
        response.put("totalPages", pagelist.getTotalPages());
        return response;
    }

    public static java.sql.Date convertStringToSqlDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new java.sql.Date(parsedDate.getTime());
    }
}

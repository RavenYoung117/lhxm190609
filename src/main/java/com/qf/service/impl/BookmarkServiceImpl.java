package com.qf.service.impl;

import com.qf.dao.BookmarkMapper;
import com.qf.dto.BookMarkDto;
import com.qf.entity.Bookmark;
import com.qf.service.BookmarkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-16 22:15
 * description:妈妈我想吃烤山药
 */
@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Resource
    private BookmarkMapper bookmarkMapper;

    @Override
    @Transactional
    public int addbookmark(String bname,String decr) {
        Bookmark bookmark = new Bookmark();
        Date date = new Date();
        bookmark.setbName(bname);
        bookmark.setbDate(date);
        bookmark.setdDecr(decr);
        int insertbookmark = bookmarkMapper.insertSelective(bookmark);
        return insertbookmark;
    }

    @Override
    public List<Bookmark> findbookmark() {
        List<Bookmark> bookmarkList = bookmarkMapper.findbookmark();
        return bookmarkList;
    }

    @Override
    public List<BookMarkDto> findBookMark(int id) {
        List<Bookmark> bookMark = bookmarkMapper.findBookMark(id);
        List<BookMarkDto> bookMarkDtos=new ArrayList<>();
        for (Bookmark mark : bookMark) {
            BookMarkDto bookMarkDto=new BookMarkDto();
            bookMarkDto.setbId(mark.getbId());
            bookMarkDto.setbName(mark.getbName());
            bookMarkDtos.add(bookMarkDto);
        }
        return bookMarkDtos;
    }

    @Override
    public int addBookMark(Bookmark bookMark) {
        int i = bookmarkMapper.addBookMark(bookMark);
        return i;
    }
}

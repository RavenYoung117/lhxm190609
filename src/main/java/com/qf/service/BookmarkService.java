package com.qf.service;

import com.qf.dto.BookMarkDto;
import com.qf.entity.Bookmark;

import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-16 22:14
 * description:妈妈我想吃烤山药
 */
public interface BookmarkService {
    int addbookmark(String bname, String bdecr);

    List<Bookmark> findbookmark();

    public List<BookMarkDto> findBookMark(int id);
    public int addBookMark(Bookmark bookMark);
}

package net.study.util;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/27/15 | 8:21 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class Paging {

    private int requestPage;        // 요청 페이지 번호
    private int totalPageCount;     // 전체 페이지 수
    private int firstRow;           // 첫 줄
    private int endRow;             // 마지막 줄
    private int beginPage;          // 시작 페이지
    private int endPage;            // 끝 페이지v

    /*
    페이징
     */
    public Paging paging(int requestPage, int countPerPage, int totalCount) {

        final int PAGING_PAGE = 10;
        int totalPageCount = 0;
        int firstRow = 0;
        int endRow = 0;
        int beginPage = 0;
        int endPage = 0;

        if(totalCount != 0 || requestPage < 0) {
            /*
            전체 게시글 개수로부터 전체 페이지 개수를 구해주는 부분
            */
            // 총 게시글 : 31 / 페이지 당 글 : 10 일때
            totalPageCount = totalCount / countPerPage;         // pageCount : 3
            if(totalCount % countPerPage > 0) {
                totalPageCount++;                               // 나머지가 1이므로 pageCount : 4
            }

            /*
            게시글의 첫 줄과 마지막 줄을 구하는 부분
             */
            firstRow = (requestPage - 1) * countPerPage + 1;
            endRow = firstRow + countPerPage - 1;               // 자기 자신도 포함되니 하나를 빼주어야 한다.

            if(endRow > totalCount){
                endRow = totalCount;
            }

            /*
            페이지의 시작과 끝을 알려주는 부분
             */
            if(totalCount != 0) {
                beginPage = (requestPage - 1) / PAGING_PAGE * PAGING_PAGE + 1;
                endPage = beginPage + PAGING_PAGE - 1;
                if(endPage > totalPageCount){
                    endPage = totalPageCount;
                }
            }
        } else {
            requestPage = 0;
        }

        Paging paging = new Paging(requestPage, totalPageCount, firstRow, endRow, beginPage, endPage);

        return paging;
    }

    public Paging() {
    }

    public Paging(int requestPage, int totalPageCount, int firstRow, int endRow, int beginPage, int endPage) {
        this.requestPage = requestPage;
        this.totalPageCount = totalPageCount;
        this.firstRow = firstRow;
        this.endRow = endRow;
        this.beginPage = beginPage;
        this.endPage = endPage;
    }

    public int getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(int requestPage) {
        this.requestPage = requestPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}

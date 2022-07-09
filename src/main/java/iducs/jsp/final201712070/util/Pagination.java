package iducs.jsp.final201712070.util;

public class Pagination {
    private int totalRows;  //전체 행(데이터)의 개수
    private int perPage;    //페이지당 표시할 행 개수
    private int perPagination;//화면에 표시할 페이지의 개수
    private int firstRow;   //현페이지에서 시작할 행 번호
    private int endRow;     //현페이지에서 마지막 행 번호

    private int totalPages; //전체 페이지의 수
    private int curPageNo;  //현제 페이지 번호
    private int beginPageNo;//표시될 시작 페이지 번호
    private int endPageNo;  //표시될 마지막 페이지 번호


    public Pagination(int curPageNo, int perPage, int perPagination, int totalRows){
        firstRow = (curPageNo -1) * perPage +1;//페이지당3개일경우 첫행 번호는 1,4,7,,...
        endRow = firstRow + perPage - 1;     //첫행번호부터 페이지당 표시개수 -1(첫행을 빼야되서)
//System.out.println("firstROw::"+firstRow);
//        System.out.println("endROw::"+endRow);
//        System.out.println("beginpa::"+beginPageNo);
//        System.out.println("endpage::"+endPageNo);
//        System.out.println("totalrows::"+totalRows);


        totalPages = totalRows / perPage;   //페이지 계산
        System.out.println("totalpage::"+totalPages);

        if((totalRows % perPage) > 0)       //나누어 떨어지지 않으면 페이지1개 추가
            totalPages++;

        beginPageNo=0;
        endPageNo=0;
        if(totalPages > 0){
            beginPageNo =  (curPageNo - 1) / perPagination * perPagination + 1;
            endPageNo = beginPageNo + perPagination - 1;
            if(endPageNo > totalPages)
                endPageNo = totalPages;
        }
        System.out.println("curPageNo:"+curPageNo);
        System.out.println("perPagination:"+perPagination);
        System.out.println("totalRows:"+totalRows);
        System.out.println("perPage:"+perPage);
        System.out.println("firstRow:"+firstRow);
        System.out.println("endRow:"+endRow);
        System.out.println("endRow:"+endRow);
        System.out.println("totalPages:"+totalPages);
        System.out.println("beginPageNo:"+beginPageNo);
        System.out.println("endPageNo:"+endPageNo);


        setPerPage(perPage);
        setBeginPageNo(beginPageNo);
        setEndPageNo(endPageNo);
        setCurPageNo(curPageNo);
        setTotalPages(totalPages);
        setPerPagination(perPagination);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPerPagination() {
        return perPagination;
    }

    public void setPerPagination(int perPagination) {
        this.perPagination = perPagination;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurPageNo() {
        return curPageNo;
    }

    public void setCurPageNo(int curPageNo) {
        this.curPageNo = curPageNo;
    }

    public int getBeginPageNo() {
        return beginPageNo;
    }

    public void setBeginPageNo(int beginPageNo) {
        this.beginPageNo = beginPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }
}

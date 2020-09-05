package model;

public class Comment {
    private Seller seller ;
    private Product product;
    private String commentText ;
    private CommentStatus status ;

    public Comment(Seller seller,Product product,String commentText){
        this.seller = seller;
        this.product = product;
        this.commentText = commentText;
        status = CommentStatus.AWAITING ;
    }

    public void setCommentStatus(CommentStatus status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return commentText ;
    }
    public CommentStatus getStatus() {
        return status ;
    }
}

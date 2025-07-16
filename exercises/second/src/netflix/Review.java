package netflix;

public class Review {
    private String text;
    private int score;
    private String reviewDate;
    private String author;

    public Review(String author, String text, int score, String reviewDate){
        this.author = author;
        this.reviewDate = reviewDate;
        this.score = score;
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString(){
        return "Review ["+text+", score="+score+", reviewDate="+reviewDate+"]";
    }
}

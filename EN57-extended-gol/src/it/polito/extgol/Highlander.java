package it.polito.extgol;

public class Highlander extends Cell{

    public Highlander() {
        super();
        this.cellType = cellType.HIGHLANDER;
        this.lifepoints = 2;    //to have 2 lifepointbonus (in order to survive 3 more times)
    }

    public Highlander(Coord tileCoord){
        super(tileCoord);
        this.cellType = cellType.HIGHLANDER;
        this.lifepoints = 2;
    }

    public Highlander(Coord tileCoord, Tile t, Board b, Game g){
        super(tileCoord, t, b, g);
        this.cellType = cellType.HIGHLANDER;
        this.lifepoints = 2;
    }

    @Override
    public Boolean evolve(int aliveNeighbors){
       int modifier = tile.getLifePointModifier();
        if(this.isAlive)
            this.lifepoints += modifier;

        Boolean willLive = this.isAlive;

        if (aliveNeighbors > 3) {
            willLive = false;
        }
        else if (aliveNeighbors < 2) {
            willLive = false;
        }
        else if (!this.isAlive && aliveNeighbors == 3) {
            willLive = true;
        }

        if(this.isAlive){
            if(willLive)
                this.lifepoints++;
            else
                this.lifepoints--;
        }

        else{
            if(willLive)
                this.lifepoints=2;  //change in reset lifepoints
        }

        if(willLive && this.lifepoints<0)
            willLive=false;
        if(!willLive && this.lifepoints>=0) //the only conceptual change
            willLive=true;

        return willLive;
    };

}    
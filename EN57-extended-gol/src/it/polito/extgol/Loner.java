package it.polito.extgol;

public class Loner extends Cell{
    
    public Loner(){
        super();
        this.cellType=cellType.LONER;
    }

    public Loner(Coord tileCoord){
        super(tileCoord);
        this.cellType = cellType.LONER;
    }

    public Loner(Coord tileCoord, Tile t, Board b, Game g){
        super(tileCoord, t, b, g);
        this.cellType=cellType.LONER;
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
        else if (aliveNeighbors < 1) {  //change from the classic rules
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
                this.lifepoints=0;
        }

        if(willLive && this.lifepoints<0)
            willLive=false;

        return willLive;

    }
}

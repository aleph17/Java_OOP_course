package it.polito.extgol;

public class Social extends Cell{
    
    public Social(){
        super();
        this.cellType=cellType.SOCIAL;
    }

    public Social(Coord tileCoord){
        super(tileCoord);
        this.cellType = cellType.SOCIAL;
    }

    public Social(Coord tileCoord, Tile t, Board b, Game g){
        super(tileCoord, t, b, g);
        this.cellType=cellType.SOCIAL;
    }

    @Override
    public Boolean evolve(int aliveNeighbors){
        int modifier = tile.getLifePointModifier();
        if(this.isAlive)
            this.lifepoints += modifier;

        Boolean willLive = this.isAlive;

        if (aliveNeighbors > 8) {   //change from classic rules
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
                this.lifepoints=0;
        }

        if(willLive && this.lifepoints<0)
            willLive=false;

        return willLive;
    }

}

public class Adapter {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(3);
        RoundBlock cylinder = new RoundBlock(3);
        System.out.println(hole.fits(cylinder));

        SquareBlock bigSquareBlock = new SquareBlock(6);
        SquareBlock smallSquareBlock = new SquareBlock(3);
        // System.out.println(hole.fits(bigSquareBlock));
        SquareBlockAdapter bigSquareBlockAdapted = new SquareBlockAdapter(bigSquareBlock);
        SquareBlockAdapter smallSquareBlockAdapted = new SquareBlockAdapter(smallSquareBlock);

        System.out.println(hole.fits(bigSquareBlockAdapted));
        System.out.println(hole.fits(smallSquareBlockAdapted));

    }
}

class RoundHole {
    private double rad;

    RoundHole(double rad) {
        this.rad = rad;
    }

    public double getRad() {
        return rad;
    }

    public boolean fits(RoundBlock block) {
        return this.getRad() >= block.getRad();
    }
}

class RoundBlock {
    private double rad;

    /*
     * UGLY!
     * RoundBlock() {
     * }
     */

    RoundBlock(double rad) {
        this.rad = rad;
    }

    public double getRad() {
        return rad;
    }

}

class SquareBlock {
    private double sideLen;

    SquareBlock(double sideLen) {
        this.sideLen = sideLen;
    }

    public double getSideLen() {
        return sideLen;
    }
}

class SquareBlockAdapter extends RoundBlock {

    private SquareBlock block;

    SquareBlockAdapter(SquareBlock block) {
        // SOLUTION!
        super(block.getSideLen());
        this.block = block;
    }

    public double getRad() {
        return block.getSideLen() * Math.sqrt(2) / 2;
    }
}
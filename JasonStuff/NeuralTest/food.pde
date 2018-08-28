class Food{
  int x;
  int y;
  int size=100;
  int siz;
  Food(){
  reset();  
  }
  void reset(){
    x=(int)random(size,boardsize-size);
    y=(int)random(size,boardsize-size);
  }
  void draw(){
    ellipseMode(CENTER);
    fill(255,0,255);
    stroke(0);
    siz=(int)pow(size,0.5);
    ellipse(x+boardx,y+boardy,siz*2,siz*2);
  }
}

float mod(float x,float n){ return(x%n+n)%n;}

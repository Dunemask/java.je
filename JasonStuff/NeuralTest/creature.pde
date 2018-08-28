class Creature{
  boolean alive;
  float x;
  float y;
  float vx;
  float vy;
  float dir;
  float mass;
  float size;
  float damping = 0.9;
  float speed=0.03;
  float rotspeed=0.1;
  float movework=0.1;
  float hunger=0.01;
  float fitness;
  public NeuralNetwork nn;
  Creature(){
    nn = new NeuralNetwork(3,new int[]{3,3,4},3);
    resetCreature();
    //nn.MakeRandomConnections(-1,1);
    //nn.node[0]=new float[]{random(-1,1),2,2};
    //nn.runThrough();
    //nn.drawNet(30,300,20,20,30,5);
  }
  void resetCreature(){
    alive=true;
    mass=200;
    size=10;
    x=(int)random(size,boardsize-size);
    y=(int)random(size,boardsize-size);
    dir=0+random(0,2*PI);
    vx=0;
    vy=0;
    fitness=0;
  }
  void resetNetwork(){
    nn.MakeRandomConnections(-1,1);
  }
  void updateInputs(){
    int[] fo = closestFood();
    Food g = f[fo[0]];
    int[] go = closestCreature();
    Creature d = c[go[0]];
    //nn.node[0]=new float[]{(1000/(100+fo[1])),mod((dir-atan2(y-g.y,x-g.x)),(PI*2))-PI,(1000/(100+go[1])),mod((dir-atan2(y-d.y,x-d.x)),(PI*2))-PI,mass,d.mass,1};
    //nn.node[0]=new float[]{(1000/(100+fo[1])),mod((dir-atan2(y-g.y,x-g.x)),(PI*2))-PI,(1000/(100+go[1])),dist(0,0,vx,vy),1};
    nn.node[0]=new float[]{(1000/(100+fo[1])),mod((dir-atan2(y-g.y,x-g.x)),(PI*2))-PI,1};
    if(fo[1]<size){
      mass+=g.size;
      fitness+=1;
      g.reset();
    }
    /*if(go[1]<size){
      mass+=d.mass;
      fitness+=2;
      d.alive=false;
    }*/
    nn.runThrough();
  }
  void draw(){
    if(alive){
      fill(0,200,0);
    }else{
      fill(120,120,120);
    }
    strokeWeight(1);
      ellipseMode(CENTER);
      stroke(0);
      ellipse(x+boardx,y+boardy,size*2,size*2);
    stroke(255);
    line(x+boardx,y+boardy,x+boardx+(float)Math.cos(dir)*size,y+boardy+(float)Math.sin(dir)*size);
    if(dist(mouseX-boardx,mouseY-boardy,x,y)<size){
      nn.drawNet(20,100,10,5,20,1);
      textAlign(LEFT);
      String s = "fit: "+ (float)((int)(fitness*10))/10;
      fill(0);
      text(s,10,10);
    }
  }
  void update(){
    if(alive){
      updateInputs();
      relativeForce(speed*nn.output[0],speed*nn.output[1]);
      mass-=0.01+hunger*size+(abs(nn.output[1])+abs(nn.output[0]))*movework+abs(nn.output[2]*1);
      size=(float)pow(mass,0.5);
      dir+=nn.output[2]*rotspeed;
      dir=mod(dir,(2*PI));
      move();
      fitness+=0.1;
      if(mass<0){
        alive=false;
        size=10;
      }  
    }
  }
  void relativeForce(float dx,float dy){
    vx+=(dx*Math.cos(dir)-dy*Math.sin(dir));
    vy+=(dy*Math.cos(dir)+dx*Math.sin(dir));
  }
  void move(){
    if(x+vx>size&&x+vx<boardsize-size){
      x+=vx;
    }
    if(y+vy>size&&y+vy<boardsize-size){
      y+=vy;
    }
    vx*=damping;
    vy*=damping;
  }
  int[] closestFood(){
    int i=0;
    int range=1000;
    for(int k = 0; k < f.length; k++){
      int d = (int)dist(x,y,f[k].x,f[k].y);
      if(d<range){
        range=d;
        i=k;
      }
    }
    return new int[]{i,range};
  }
  int[] closestCreature(){
    int i=0;
    int range=1000;
    for(int k = 0; k < c.length; k++){
      if(c[k]!=this&&c[k].alive){
        int d = (int)dist(x,y,c[k].x,c[k].y);
        if(d<range){
          range=d;
          i=k;
        }
      }
    }
    return new int[]{i,range};
  }
}

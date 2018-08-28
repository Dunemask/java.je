//TODO Make it take the top 10

int x = 0;
int skip = 10;
int fullsize=1000;
int boardsize=800;
int boardx=100;
int boardy=100;
int sel=0;
int most;
int time;
int maxtime=1000;
boolean play;
Slider skips = new Slider(1,100,true,100,50,200,"Skips");
Slider maxtim = new Slider(500,5000,true,350,50,200,"Max Time");
Food[] f = new Food[30];
Creature[] c = new Creature[30];
void setup(){
  sel=0;
  play=true;
 for(int i = 0; i<c.length;i++){
   c[i] = new Creature();
   c[i].resetNetwork();
  }
  for(int i = 0; i<f.length;i++){
   f[i] = new Food();
  }
  frameRate(30);
  size(1000,1000);
  randomSeed(0);
}
void draw(){
  fill(170);
  rectMode(CORNER);
  rect(0,0,fullsize,fullsize);
  fill(220);
  rect(boardx,boardy,boardsize,boardsize);
  for(int k = 0;k<skip;k++){
    if(play){time++;}
  for(int i = 0; i<c.length;i++){
    if(play){
      c[i].update();
    }
    if(k==0){c[i].draw();}
     }
    if(k==0){
      for(int i = 0;i<f.length;i++){
        f[i].draw();
      }
    }
  }
  if(time>maxtime||allDead()){ 
    int g = findGreatest();
    NeuralNetwork x = c[g].nn;
    //x.connections = c[g].nn.connections;
    for(int i = 0;i<c.length;i++){
    c[i].nn.copyConnections(x);
    c[i].nn.mutate((int)random(0,3),0.7);
    c[i].resetCreature();
    time=0;
  }
  c[0].nn= x;
    
    fill(255,0,0);
    rect(boardx,boardy,boardsize,boardsize);
  }
  c[sel].nn.drawNet(20,250,10,5,20,1);
  fill(0);
  textSize(20);
  textAlign(RIGHT);
  text("time:"+time,fullsize-10,30);
  upyou();
  skips.draw();
  skip=(int)skips.value;
  maxtim.draw();
  maxtime=(int)maxtim.value;
}
boolean allDead(){
  boolean x = true;
  for(int i = 0;i<c.length;i++){
    if(c[i].alive){
      x=false;
    }
  }
  return x;
}
void keyPressed(){
  if(key == 's'){
    if(play){
      play=false;
    }else{
      play=true;
    }
  }
}
void upyou(){
  if(keyPressed){
  if(key=='w'){
    c[sel].relativeForce(1,0);
  }
  if(key=='a'){
    c[sel].dir+=0.3;
  }
  if(key=='d'){
    c[sel].dir-=0.3;
  }
}
}
int findGreatest(){
  int k = 0;
  float max=0;
  for(int i = 0;i<c.length;i++){
    if(c[i].fitness>max){
      max=c[i].fitness;
      k=i;
    }
  }
  return k;
}

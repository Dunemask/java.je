class Slider{
  int x;
  int y;
  int len;
  float val;
  String label;
  boolean toint;
  float min;
  float max;
  float value;
  Slider(float min, float max, boolean toint, int x,int y,int len,String s){
  this.x=x;
  this.y=y;
  this.len=len;
  this.val=0.5;
  this.value=(min+max)*0.5;
  this.label=s;
  this.min=min;
  this.max=max;
  this.toint=toint;
  }
  void draw(){
    strokeWeight(2);
    stroke(0);
    line(x,y,x+len,y);
    if((mouseX-x)>0&&mouseX-x<len&&abs(mouseY-y)<10){
      if(mousePressed){
        val = (mouseX-x)/(float)len;
      }
    }
    fill(100);
    strokeWeight(1);
    ellipseMode(CENTER);
    ellipse(x+len*val,y,10,10);
    fill(0);
    textSize(10);
    textAlign(CENTER);
    value = map(val,0,1,min,max);
    if(toint){
      value=(int)value;
      val = map(value,min,max,0,1);
    }
    text(label+":"+value,x+len*val,y+20);
  }
}

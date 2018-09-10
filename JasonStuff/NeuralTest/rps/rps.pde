NeuralNetwork nn = new NeuralNetwork(2,new int[]{4},3);
void setup(){
  size(500,500);
  frameRate(10);
  nn.MakeRandomConnections(
 0,1);
}
boolean go=true;
int field;
void draw(){
   fill(255);
   rectMode(CENTER);
   rect(250,250,500,500);
  nn.draw(50,250,20,20,20,5);
  if(go){
    field=(int)(random(0,3)); 
  }
  float[] input = new float[]{0,1};
  float[] want = new float[]{0,0,0};
  if(field == 0){;
    input = new float[]{1,1};
    want = new float[]{0,1,0};
  }
  if(field == 1){;
    input = new float[]{2,1};
    want = new float[]{0,0,1};
  }
  if(field == 2){;
    input = new float[]{3,1};
    want = new float[]{1,0,0};
  }
  nn.node[0]=input;
  nn.runThrough();
  float cost = nn.costF(want);
  print(cost);
  nn.UpConnect(want,0.1,-2);
}

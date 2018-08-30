int x = 0;
int time=0;

int letters = 6;
String test = "bob";
String name = "boyi";
int field =0;
boolean go = true;
float[] want;
String[] list1;
String[] list2;
String[][] names;
String alphabet="abcdefghijklmnopqrstuvwxyz";
int[] store = new int[100];
Slider strength = new Slider(-20.0,-0.001,false,10,60,100,"Strength");
float confidence;


NeuralNetwork nn = new NeuralNetwork(26*letters,new int[]{16},2);
void setup(){
  list1=loadStrings("list1.txt");
  list2 = loadStrings("list2.txt");
  names= new String[][]{list1,list2};
  frameRate(20);
  size(1000,1000);
  randomSeed(0);
  nn.MakeRandomConnections(0,0.1);
}
void draw(){
  fill(170);
  rectMode(CORNER);
  rect(0,0,1000,1000);
  if(go){
  field=(int)(random(0,2));  
  }
  if(field==0){
    want = new float[]{1,0};
  }else{
    want = new float[]{0,1};
  }
  
  float[] input = new float[26*letters];
  if(go){
  name = names[field][int(random(0,names[field].length))];
  }else{
  name = test;
  }
  for(int i = 0;i<letters&&i<name.length();i++){
  input[alphabet.indexOf(name.toLowerCase().charAt(i))+26*i] = 1;
  }
  
  
  nn.node[0]=input;
  nn.runThrough();
  float cost = nn.costF(want);
   fill(0);
  textSize(20);
  textAlign(RIGHT);
  text("Cost:"+nf(cost,1,3),990,30);
  text("Name:"+name,990,60);
  text("Field:"+field,990,90);
  int guess = 0;
  float max=0;
  for(int i = 0;i<nn.output.length;i++){
    if(nn.output[i] >= max){
      max = nn.output[i];
      guess=i;
    }
  }
  int right=0;
  if(go){
  for(int i = 0;i<store.length-1;i++){
    store[i]=store[i+1];
  }
  }
  if(guess==field){
    store[99]=1;
  }else{
    store[99]=0;
  }
  for(int i = 0;i<store.length;i++){
    right += store[i];
  }
  confidence=(float)Math.pow(nn.output[0]-nn.output[1],2);
  text("Guess:"+guess,990,120);
  text("Confidence: "+nf(confidence,1,3),990,150);
  text("PercentRight:"+right+"/"+100,990,180);
  if(!go){
    text(test+": "+guess,990,210);
  }
  textAlign(LEFT);
  text("Time:"+time,10,30);
  
  if(go){
  nn.UpConnect(want,0.01,strength.value);
  time++;
  }else{
  nn.draw(200,500,200,-15,20,1);
  }
  strength.draw();
}
void keyPressed(){
  if (key == '1'){
    if(go){
      go=false;
    }else{
      go=true;
    }  
  }
  if(!go){
    if(alphabet.indexOf(key)>-1){
    test+=key;
    if(test.length()>letters){
      test=test.substring(1);
      }
    }else{
    if(key=='2'){
      if(test.length()>0){
        test=test.substring(0,test.length()-1);
      }
    }
    }
  }
}

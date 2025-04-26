public class HuffmanTree
{
    // public HuffmanTree(HuffmanNode huff)
    // public void printLegend()
    // public static BinaryHeap legendToHeap(String legend)
    // public static HuffmanTree createFromHeap(BinaryHeap b)
    // public static void main(String[] args)
  private HuffmanNode root;

  public HuffmanTree (HuffmanNode huff)
  {
      root= huff;
  }


  public void printLegend ()
  {
      printLegend (root,"");
  }


  private void printLegend (HuffmanNode t,String s)
  {
    if (t.letter.length()<=1)
    {
        System.out.println (t.letter+"="+s);
    }
    else
    {
        printLegend (t.left,s+"0");
        printLegend (t.right,s+"1");
    }
  }


  public static BinaryHeap legendToHeap (String legend)
  {
    BinaryHeap <HuffmanNode> heap= new BinaryHeap <HuffmanNode> ();
    String[] str= legend.split(" ");
    for (int i=0;i<str.length;i+=2)
    {
       String word= str[i];
       double fr= Double.parseDouble (str[i+1]);
       heap.insert(new HuffmanNode(word,fr));
    }
    return heap;
  }


  public static HuffmanTree createFromHeap (BinaryHeap b)
  {
      while (b.getSize()>1)
      {
          HuffmanNode b1= (HuffmanNode) b.deleteMin();
          HuffmanNode b2 =(HuffmanNode) b.deleteMin();
          HuffmanNode b3= new HuffmanNode (b1,b2);
          b.insert (b3);
      }
      HuffmanTree rt= new HuffmanTree ((HuffmanNode)b.findMin());
      return rt;
  }


  public static void main (String[] args)
  {
      String legend= "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
      BinaryHeap h= legendToHeap (legend);
      h.printHeap();
      HuffmanTree rt= createFromHeap (h);
      rt.printLegend();
  }


}
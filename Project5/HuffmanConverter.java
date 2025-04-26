import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class HuffmanConverter
{
    public int Number = 256;
    private String contents;
    private HuffmanTree huffmanTree;
    private int [] count;
    private String [] code;
    public HuffmanConverter (String input)
    {
        contents= input;
        count= new int [Number];
        code= new String [Number];
        for (int i=9;i<Number;i++)
        {
            count[i]=0;
            code[i]=""; 
        }
    }
    public HuffmanTree getHuffmanTree ()
    {
        return huffmanTree;
    }
    public void storeFrequencies ()
    {
         char[] charArray = this.contents.toCharArray();
            for (int i=0; i<charArray.length; i++) {
                char c = charArray[i];
                this.count[(int) c] += 1;
            }
            for (int j=0; j<this.count.length; j++) {
                if (this.count[j] > 0) {
                    if ((char)j != '\n') {
                        System.out.print("<" + (char) j + ", " + this.count[j] + ">");
                    }
                    else {
                        System.out.print("<\\n, " + this.count[j] + ">");
                    }
                }
            }
            System.out.println();
            System.out.println();
    }
    public void frequenciesToTree ()
    {
       BinaryHeap heap = new BinaryHeap();
      for (int i=0; i<this.count.length; i++) {
                if (this.count[i] > 0) {
                    HuffmanNode node = new HuffmanNode(Character.toString((char)i), (double) this.count[i]);
                    heap.insert(node);
                }
            }
      this.huffmanTree = HuffmanTree.createFromHeap(heap);
    }
    public void treeToCode ()
    {
       treeToCode (huffmanTree.getRoot(),""); 
       System.out.println();
    }
    private void treeToCode (HuffmanNode t, String encoding)
    {
       if (t.left == null && t.right == null && t != null) {
                this.code[(int)t.letter.charAt(0)] = encoding;
                if (t.letter.charAt(0) != '\n') {
                    System.out.println("\"" + t.letter + "\"=" + encoding);
                }
                else {
                    System.out.println("\"\\n" + "\"=" + encoding);
                }
            }
            else {
                treeToCode(t.left, encoding+"0");
                treeToCode(t.right, encoding+"1");
            }
    }
    public String encodeMessage ()
    {
       StringBuilder output=new StringBuilder ();
       for (char i: contents.toCharArray())
       {
           output.append (code[(int)i]);
       }
       return output.toString();
    }
    public String decodeMessage(String encodedStr)
    {
        StringBuilder output = new StringBuilder ();
        HuffmanNode current= huffmanTree.getRoot();
        for (int i=0;i< encodedStr.length();i++)
        {
          if (encodedStr.charAt(i)==0)
          {
              current=current.left;
          }
          else
          {
              current=current.right;
          }
          if (current.left==null && current.right== null)
          {
              output.append (current.letter);
              current= huffmanTree.getRoot();
          }
        }
        return output.toString();
    }
    public static String readContents(String filename) 
    {
            String temp = "";
            try     {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
    }
    public static void main(String args[]) {
                String input = HuffmanConverter.readContents("love_poem_80.txt");
                HuffmanConverter h1 = new HuffmanConverter(input);
                h1.storeFrequencies();
                h1.frequenciesToTree();
                h1.treeToCode();
                h1.getHuffmanTree().printLegend();
                String encoded = h.encodeMessage();
                System.out.println("\nHuffman Encoding:\n"+encoded+"\n");
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded));
        }
}
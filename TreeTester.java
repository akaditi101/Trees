
/**
 * TreeTester.java  
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id 0000000
 *
 * @author - I received help from ...
 *
 */ 
public class TreeTester
{
    public static void main(String[] args)
    {
        String[] names = {"James", "Matt", "Amy", "Stephen", "Caleb", "Joseph",
                          "Holly", "Morgan", "Ella", "Hannah", "Dakota", "Oliver"};
        Tree tree = new Tree();
        for (String s : names)
        {
             tree.add(s);   
        }
        tree.inorder();
        System.out.println("The least amoung us is " + tree.min());
        System.out.println("Tree contains Noah? " + tree.contains2("Noah"));
        System.out.println("Count of nodes = " + tree.countNodes());
        System.out.println("Count of  parent nodes = " + tree.countParents());
        System.out.println("Count of  leaf nodes = " + tree.countLeaves());
        
        System.out.println("There are" + tree.countParents() + " parent nodes.");
        
        
        System.out.println();
        
        System.out.println("Tree is full? " + tree.isFull());
        System.out.println("Number of levels is " + tree.levels());
        
        tree.levelOrder();
    }
}

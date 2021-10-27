import java.util.*;
public class Tree
{
    private TreeNode<Comparable> root;

    public Tree()
    {
        root = null;   
    }

    public void add(Comparable value)
    {
        root = add(root, value); //calling private helper function
    }

    private TreeNode<Comparable> add(TreeNode<Comparable> t, Comparable value)
    {
        if (t == null)
        {
            return new TreeNode<Comparable>(value);
        }

        if (value.compareTo(t.getValue()) < 0)
        {
            t.setLeft(add(t.getLeft(), value)); // set on the left what is added on left
        }
        else
        {
            t.setRight(add(t.getRight(), value));
        }
        return t;
    }
    
    public void delete(Comparable val)
    {
        root = delete(root, val);
    }
    
    private TreeNode<Comparable> delete(TreeNode<Comparable> t, Comparable val)
    {
        //search for val
        if (t == null)
        {
            return t;
        }
        else if (val.compareTo(t.getValue()) < 0)
        {
            t.setLeft(delete(t.getLeft(), val));
        }
        else if (val.compareTo(t.getRight()) > 0)
            t.setRight(delete(t.getRight(), val));
        else
        {
            if (t.getRight() == null && t.getLeft() == null)
            {
                return null;
            }
            if (t.getLeft() == null)
            {
                return t.getRight();
            }
            if (t.getRight() == null)
                return t.getLeft();
            // This node has two children!!!!!
            TreeNode<Comparable> tMin = min(t.getRight());
            t.setValue(tMin.getValue());
            t.setRight(delete(t.getRight(), tMin.getValue()));
        }
        return t;
    }
    
    public boolean contains(Comparable c)
    {
      return contains(root, c);
    }
    
    private boolean contains(TreeNode<Comparable> t, Comparable c)
    {
      if (t == null) 
          return false;
      if (t.getValue().equals(c)) 
          return true;
      if (c.compareTo(t.getValue()) < 0) 
         return contains(t.getLeft(), c);
      return contains(t.getRight(), c);
    }
    
    public boolean contains2(Comparable c)
    {
      TreeNode<Comparable> t = root;
      while (t != null)
      {
         if (t.getValue().equals(c)) 
             return true;
         if (c.compareTo(t.getValue()) < 0)
           t = t.getLeft();
         else
           t = t.getRight();
      }
      return false;
    }
    
    public Comparable min()
    {
      return min(root).getValue();
    }
    
    private TreeNode<Comparable> min(TreeNode<Comparable> t)
    {
      if (t == null) 
          return null;
      if (t.getLeft() == null) 
          return t;
      return min(t.getLeft());
    }
    
    public Comparable min2()
    {
      TreeNode<Comparable> t = root;
      if (t == null) return null;
      while (t.getLeft() != null)
         t = t.getLeft();
      return t.getValue();

    }
    
    public int countNodes()
    {
      return countNodes(root);
    }
    
    private int countNodes(TreeNode<Comparable> t)
    {
         if (t == null) return 0;
         return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }
    
    public int countParents()
    {
      return countParents(root);
    }
    
    private int countParents(TreeNode<Comparable> t)
    {
      if (t == null) 
          return 0;
      if (isLeaf(t)) 
          return 0;
      return 1 + countParents(t.getLeft()) + countParents(t.getRight());
    }
    
    private boolean isLeaf(TreeNode t)
    {
      return t.getLeft() == null && t.getRight() == null;
    }
    
    private boolean hasOnlyOneChild(TreeNode t)
    {
      return t.getLeft() == null && t.getRight() != null ||
             t.getLeft() != null && t.getRight() == null;
    }
    
    private boolean hasTwoChildren(TreeNode t)
    {
      return t.getLeft() != null && t.getRight() != null;
    }
    
    public int countLeaves()
    {
      return countLeaves(root);
    }
    
    private int countLeaves(TreeNode t)
    {
      if (t == null) 
          return 0;
      if (isLeaf(t)) 
          return 1;
      return 0 + countLeaves(t.getLeft()) + countLeaves(t.getRight());
    }
    
    public int countOneChildParents()
    {
      return countOneChildParents(root);
    }
    
    private int countOneChildParents(TreeNode t)
    {
      if (t == null) 
          return 0;
      if (hasOnlyOneChild(t)) 
          return 1 + countOneChildParents(t.getLeft()) + countOneChildParents(t.getRight());
      else
      {
          return countOneChildParents(t.getLeft())+ countOneChildParents(t.getRight());
        }
    }
    
    public int countTwoChildParents()
    {
        return countTwoChildrenParents(root);
    }
    
    private int countTwoChildrenParents(TreeNode<Comparable> t)
    {
        if (t==null)
        {
            return 0;
        }
        
        if (hasTwoChildren(t))
        {
            return 1 + countTwoChildrenParents(t.getLeft()) + countTwoChildrenParents(t.getRight());
        }
        else
        {
            return countTwoChildrenParents(t.getLeft()) + countTwoChildrenParents(t.getRight());
        }
    }
    
    public boolean isFull()
    {
        return isFull(root);
    }
    
    private boolean isFull(TreeNode t)
    {
        if (t == null || isLeaf(t))
            return true;
        if (isLeaf(t))
            return true;
        return hasTwoChildren(t) && isFull(t.getLeft()) && isFull(t.getRight());
    }

    public void inorder()
    {
        System.out.println("Inorder print of the tree...");
        inorder(root);
    }
    
    public int levels()
    {
        return levels(root);
    }
    
    private int levels(TreeNode t)
    {
        if (t == null)
            return 0;
        int left = levels(t.getLeft());
        int right = levels(t.getRight());
        if (left > right)
            return 1 + left;
        return 1 + right;
    }
    
    public int height()
    {
        return levels(root) - 1;
    }
    
    public boolean isPerfect()
    {
        return countNodes(root) == Math.pow(2, levels()) -1;
    }
    
    public boolean isBalanced()
    {
        return isBalanced(root);
    }
    
    public boolean isBalanced(TreeNode t)
    {
        if (t == null)
            return true;
        int left = levels(t.getLeft());
        int right = levels(t.getRight());
        return Math.abs(left - right) <= 1 && isBalanced(t.getLeft()) && isBalanced(t.getRight());
    }

    private void inorder(TreeNode<Comparable> t)
    {
        if (t != null)
        {
            inorder(t.getLeft());
            System.out.println(t.getValue());
            inorder(t.getRight());
        }
    }
    
    public void levelOrder()
    {
        System.out.println("Level Order Print");
        levelOrder(root);
        System.out.println();
    }
    
    private void levelOrder(TreeNode t)
    {
        if (t!= null)
        {
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.add(t);
            while(!q.isEmpty())
            {
                TreeNode temp = q.remove();
                System.out.println(t.getValue() + " ");
                if (t.getLeft() != null)
                    q.add(t.getLeft());
                if (t.getRight() != null)
                    q.add(t.getRight());
            }
        }
            
    }
    
    public Tree copyTree()
    {
        Tree copy = new Tree();
        copy.root = copyTree(root);
        return copy;
    }
    
    private TreeNode<Comparable> copyTree(TreeNode<Comparable> t)
    {
        if (t==null)
            return null;
        return new TreeNode(t.getValue(), copyTree(t.getLeft()), copyTree(t.getRight()));
    }
    
    private void preorder(TreeNode<Comparable> t)
    {
        if (t != null)
        {
            System.out.println(t.getValue());
            preorder(t.getLeft());
            preorder(t.getRight());
        }
    }
    private void postorder(TreeNode<Comparable> t)
    {
        if (t != null)
        {
            postorder(t.getLeft());
            postorder(t.getRight());
            System.out.println(t.getValue());
        }
    }
}
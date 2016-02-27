/* 
 * Objects of the IntCollection class represent collections of
 * positive integers with no repeated integers.
 * This version of the IntCollection class stores the ints in
 * binary search trees made up of individual nodes.
 * All public methods have the same documentation as previous
 * versions of the class, but private methods have been added
 * to allow recursive traversal of the binary tree.
 * 
 * Kelsey Mullin
 * 2/24/16
 */

public class IntCollection
{
	   private btNode collection;
	   private int howMany;

	   //Represents nodes of a binary tree that store one int and references
	   //to a left and right subtree (each of which is also represented by an
	   //object of the btNode class)
	   private static class btNode
	   {
	        private int info;
	        private btNode left, right;

	        public btNode()
	        {
	            info = 0;
	            left = null;
	            right = null;
	        }

	        public btNode(int i, btNode lt, btNode rt)
	        {
	            info = i;
	            left = lt;
	            right = rt;
	        }
	   }
	   
	   //Create a default collection with space for 500
	   //elements that is initially empty
	   public IntCollection()
	   {
	      	collection = null;
	      	howMany = 0;
	   }
	   
	   //Create a collection with space for specified # of elements
	   //that is initially empty
	   public IntCollection(int i)
	   {
		   	collection = null;
		   	howMany = 0;
	   }
	   
	   //Recursive method used by copy method to successively copy
	   //each subtree with pre-order traversal
	   private static btNode copyTree(btNode t)
	   {
		  btNode root = null;
		  if(t != null)
		  {
			  root = new btNode();
			  root.info = t.info;
			  root.left = copyTree(t.left);
			  root.right = copyTree(t.right);
		  }
		  return root;
	   }
	   
	   //Copy contents of obj to calling object
	   public void copy(IntCollection obj)
	   {								
	      	if (this != obj)
	      	{
	      		howMany = obj.howMany;
	      		collection = copyTree(obj.collection);
	      	}
	   }

	   //Check collection to see if it contains integer i
	   public boolean belongs(int i)	
	   {	

		   btNode traverse = collection;
		   while(traverse != null && traverse.info != i)
		   {
			   if(i < traverse.info)
				   traverse = traverse.left;
			   else
				   traverse = traverse.right;
		   }
		   return (traverse != null);
	   }
	   
	   //Insert i into collection if it's positive
	   //and not already present in collection
	   public void insert(int i)
	   {							
		   if (i > 0)
		   {
			   	btNode traverse = collection, pred = null;
				while(traverse != null && traverse.info != i)
				{
					pred = traverse;
					if(i < traverse.info)
						traverse = traverse.left;
					else
						traverse = traverse.right;
				}
				if(traverse == null)
				{
					howMany++;
					traverse = new btNode(i, null, null);
					if(pred != null)
					{
						if(i < pred.info)
							pred.left = traverse;
						else
							pred.right = traverse;
					}
					else
						collection = traverse;
				}
		   }
	   }
	   
	   //Remove positive integer i if it is present in collection
	   public void omit(int i)
	   {
		   if (i > 0)
		   {
			    btNode traverse = collection, pred = null;
			    while(traverse != null && traverse.info != i)
			    {
					pred = traverse;
					if(i < traverse.info)
						traverse = traverse.left;
					else
						traverse = traverse.right;
				}
			    if(traverse != null)
			    {
			    	howMany--;
			    	
			    	//If i is contained in a leaf node (whether root or not):
			    	if(traverse.left == null && traverse.right == null)
			    	{
			    		if(pred == null)
			    			collection = null;
			    		else if(traverse.info < pred.info)
			    			pred.left = null;
			    		else
			    			pred.right = null;
			    	}
			    	
			    	//If i is in a node with only a right subtree:
			    	else if(traverse.left == null)
			    	{
			    		if(pred == null)
			    			collection = traverse.right;
			    		else if(traverse.info < pred.info)
			    			pred.left = traverse.right;
			    		else
			    			pred.right = traverse.right;
			    	}
			    	
			    	//If i is in a node with only a left subtree:
			    	else if(traverse.right == null)
			    	{
			    		if(pred == null)
			    			collection = traverse.left;
			    		else if(traverse.info < pred.info)
			    			pred.left = traverse.left;
			    		else
			    			pred.right = traverse.left;
			    	}
			    	
			    	//If i is in a node with two subtrees:
			    	else
			    	{
			    		btNode traverse2 = traverse.left, pred2 = null;
			    		while(traverse2.right != null)
			    		{
			    			pred2 = traverse2;
			    			traverse2 = traverse2.right;
			    		}
			    		if(pred2 == null)
			    		{
			    			traverse2.right = traverse.right;
			    			if(pred == null)
			    				collection = traverse2;
			    			else if(traverse.info < pred.info)
				    			pred.left = traverse2;
				    		else
				    			pred.right = traverse2;
			    		}
			    		else
			    		{
			    			pred2.right = traverse2.left;
			    			traverse2.left = traverse.left;
			    			traverse2.right = traverse.right;
			    			if(pred == null)
			    				collection = traverse2;
			    			else if(traverse.info < pred.info)
				    			pred.left = traverse2;
				    		else
				    			pred.right = traverse2;
			    		}
			    	}
			    }
		   }
	   }

	   //Return number of integers in collection
	   public int getHowMany()
	   {
	      	return howMany;
	   }

	   //Recursive method used by print method to successively print
	   //each subtree with in-order traversal
	   private static void printTree(btNode t)
	   {
		   if(t != null)
		   {
			   printTree(t.left);
			   System.out.println(t.info);
			   printTree(t.right);
		   }
	   }
	   
	   //Print all integers in collection
	   public void print()
	   {
		   printTree(collection);
	   }

	   //Recursive method used by equals method to successively copy
	   //each subtree to an array with in-order traversal
	   private static int toArray(btNode t, int[] a, int i)
	   {
	      int numNodes = 0;
	      if (t != null)
	      {
	         numNodes = toArray(t.left, a, i);
	         a[numNodes + i] = t.info;   
	         numNodes = numNodes + 1 + toArray(t.right, a, (numNodes + i + 1));
	      }
	      return numNodes;
	   } 
	   
	   //Check if calling object and obj have exactly the same contents
	   public boolean equals(IntCollection obj)	
	   {
	      	boolean result = (this.howMany == obj.howMany);
	      	if(result)
	      	{
		      	int[] thisArray = new int[this.howMany];
		      	int[] objArray = new int[obj.howMany];
		      	int i = 0;
		      	toArray(this.collection, thisArray, 0);
		      	toArray(obj.collection, objArray, 0);
		      	while ((i < howMany) && result)
		      	{
		         	result = (thisArray[i] == objArray[i]); i++;
		      	}
	      	}
	      	return result;
	   }
}

package com.mycompany.digital_contact_list;

public class BSTPhoneBook {
    
    BSTNode root;
    
     public BSTPhoneBook() {
        root = null; //before creating the tree, root is null and after the first insertion it will contain the root node (person) of the tree
    }
     
    
    public void insert(Person p){
            
        if(searchRec(root, p.getName()) != null){
             System.out.println("Duplicates contact names are not allowed!!");
             return;
        }

        root = insertRec(root, p); // this line will let the root the first insertion and will never changes again
        System.out.println("Contact added successfully...");
    }
    
    private BSTNode insertRec(BSTNode node, Person p){
        
        if(node==null){
            return new BSTNode(p);
            
        }
        // FIRST.compareToIgnoreCase(SECOND) the function compareToIgnoreCase returns 0, -ve, +ve
       
        if(p.getName().compareToIgnoreCase(node.data.getName())<0) 
        // if 1st name (p) smaller compared to 2nd name node name, so it returns -ve value so (-ve<0) is true
            node.left= insertRec(node.left, p);
        
        else if(p.getName().compareToIgnoreCase(node.data.getName())>0)
        // if 1st name (p) greater compared to 2nd name node name, so it returns +ve value so (+ve>0) is true
            node.right= insertRec(node.right, p);
        
        return node;
    }
    
//    1. Start from root
//    2. Compare target name with current node name
//    3. If equal → found
//    4. If target is smaller → move to left child
//    5. If target is greater → move to right child
//    6. Repeat until found or until node becomes null
   
    public void search(String name){
    
        BSTNode result= searchRec(root, name);
        if(result!=null){
            System.out.println("Contact Found: " + 
                    result.data.getName() + "  " +
                    result.data.getPhone());
        }
        else
            System.out.println("Contact Not Found...");
        }

    
    public BSTNode searchRec(BSTNode node, String name){
        
        if(node==null)
            return null;
        
        if(name.equalsIgnoreCase(node.data.getName()))
            return node;
        
        if(name.compareToIgnoreCase(node.data.getName())<0)
            return searchRec(node.left, name);
        else
            return searchRec(node.right, name);
 
    }
    
 
    // 1. Start from root
    // 2. Compare target name with current node name
    // 3. If target is smaller → search in left subtree
    // 4. If target is greater → search in right subtree
    // 5. If target is found:
    //    - No children → remove node
    //    - One child → replace node with child
    //    - Two children → replace with inorder successor
    // 6. Repeat recursively until node is removed
   
    public BSTNode findMin(BSTNode node){
        
        while(node.left!=null){
            node= node.left;
        }
        return node;
    }
    
    public void remove(String name){
        
       if(searchRec(root, name) != null){
        root = removeRec(root, name); //  //if the removed node is the root, the root may need to change.
        System.out.println("Contact Removed Successfully...");
    }
    else
        System.out.println("Contact Not Found...");
  
    }
    
    public BSTNode removeRec(BSTNode node, String name){
        
//        if(node==null){
//            System.out.println("Contact Not Found...");
//            return null;
//        }
        
        if(name.compareToIgnoreCase(node.data.getName())<0)
            node.left= removeRec(node.left, name);
        
        else if (name.compareToIgnoreCase(node.data.getName())>0)
            node.right= removeRec(node.right, name);
        
        else{ //else is when target is found and needed to be removed
            
            //case 1: no child, node is a leaf
            if(node.left==null && node.right==null)
                return null;
            
            //case 2: one child
            else if(node.left==null)
                return node.right;
            
            else if(node.right==null)
                return node.left;
            
            else{ // we replace the smallest in the right subtree with the node called Inorder Successor
            BSTNode Successor= findMin(node.right); // return the smallest node in the right subtree
            node.data= Successor.data;  // we changed data of the node with data of the successor
            node.right= removeRec(node.right, Successor.data.getName()); //we delete the old successor node place to avoid duplicate nodes
            }
        
        }
         return node;
         
    }
    
    public void updateExistingPhone(String name, String phone){
    
        BSTNode result= searchRec(root, name);
        if(result!=null){
        result.data.setPhone(phone);
        System.out.println("Contact Phone Updated Successfully...");
        }
            
    }
    
    public void updateExistingPhone2(Person p){
    
        BSTNode result= searchRec(root, p.getName());
        if(result!=null){
        result.data.setPhone(p.getPhone());
        System.out.println("Contact Phone Updated Successfully...");
        }
        else
            System.out.println("Contact not Found!!");
            
    }
    
    private void insertWithoutMessage(Person p){
    root = insertRec(root, p);
}
    public void updateExistingName(String name, String name2){
    
        BSTNode result= searchRec(root, name);
        
        if(result!=null){
            String phone = result.data.getPhone();
            remove(name);
            insertWithoutMessage(new Person(name2, phone));
            System.out.println("Contact Name Updated Successfully...");
        }
        else
            System.out.println("Contact not Found!!");
            
    }
    
    public void display(){
        
        if(root == null){
        System.out.println("No contacts were found to be displayed...");
        return;
    }
        inorder(root); //displaying in ascending order
        System.out.println("Contacts displayed successfully...");
    }
    
    public void inorder(BSTNode node){ //left -> root -> right
    
        if(node==null)
            return;
        
        inorder(node.left);
        System.out.println(node.data.getName() + "  " + node.data.getPhone());
        inorder(node.right);
    }
    
    public void size() {
        System.out.println("Number of Contacts found is " + sizeRec(root));
}

    private int sizeRec(BSTNode node) {
         if (node == null)
            return 0;

        return 1 + sizeRec(node.left) + sizeRec(node.right);
}

    public void isolate0100(){
        isolate0100Rec(root);
    }
    public void isolate0100Rec(BSTNode node){
        
        if(node==null)
            return;
        
        isolate0100Rec(node.left);
        if(node.data.getPhone().startsWith("0100"))
            System.out.println(node.data.getName() + "  " + node.data.getPhone());
        isolate0100Rec(node.right);
        
    }
      
    }

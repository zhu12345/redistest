package cn.redis.utils.tree;

import java.util.ArrayList;
import java.util.List;


public class TreeBulid {
    /**
     * 为tree添加子节点
     * @param tree
     * @param lTree
     * @return
     */
    public static Tree addTree(Tree tree, List<Tree> lTree) {
        for (Tree t : lTree) {
            if (tree.getId() == t.getParentId()) {
                if (tree.getlTree() == null) {
                    tree.setlTree(new ArrayList<Tree>());
                }

                tree.getlTree().add(addTree(t, lTree));
            }
        }

        return tree;
    }

    /**
     * 找到指定的tree
     * @param tag
     * @param sb
     * @param lTree
     * @return
     */
    public static Tree findTree(int tag, StringBuffer sb, List<Tree> lTree) {
        Tree tt = null;

        for (Tree t : lTree) {
            if ((t.getTag() == tag) &&
                    t.getParentString().equals(sb.toString())) {
                tt = t;

                //if(ss[0]==findFarent(t,lTree).getText())
                break;
            }
        }

        return tt;
    }

    /**
     * 把所有的tree建成树结构
     * @param tree
     * @return
     */
    public static List<Tree> bulid(List<Tree> tree) {
        List<Tree> trees = new ArrayList<Tree>();
        for (Tree treeNode : tree) {
            if (0 == treeNode.getParentId()) {
                trees.add(treeNode);
            }
            for (Tree it : tree) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getlTree() == null) {
                        treeNode.setlTree(new ArrayList<Tree>());
                    }
                    treeNode.getlTree().add(it);
                    treeNode.setState("closed");
                }
            }
        }
        return trees;
    }

    /**
     *以list中的“:”为分割生成对应的树结构
     * @param list
     * @return
     */
    public static List<Tree> createTree(List<String> list) {
        List<Tree> lc = new ArrayList<Tree>();
        //树ID
        int idCreate = 1;
        for (String s : list) {
            String[] ss = s.split(":");
            for (int i = 0; i < ss.length; i++) {
                if (i == 0) {
                    if (!(lc == null)) {
                        boolean flag = false;

                        for (Tree t : lc) {
                            if ((t.getTag() == i) && t.getText().equals(ss[0])) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            lc.add(new Tree(idCreate++, 0, ss[0] + ":", ss[0], 0));
                        }
                    } else {
                        lc.add(new Tree(0, 0, ss[0] + ":", ss[0], 0));
                    }
                } else {
                    StringBuffer sb = new StringBuffer();

                    for (int i$ = 0; i$ < i; i$++) {
                        sb.append(ss[i$] + ":");
                    }
                    StringBuffer sb1 = new StringBuffer();
                    for (int i$ = 0; i$ <= i; i$++) {
                        sb1.append(ss[i$] + ":");
                    }
                    Tree correntTree = TreeBulid.findTree(i, sb1, lc);
                    Tree fatherTree = TreeBulid.findTree(i - 1, sb, lc);

                    if (correntTree == null) {
                        lc.add(new Tree(idCreate++, i, sb1.toString(), ss[i],
                                fatherTree.getId()));
                    }
                }
            }
        }
        return TreeBulid.bulid(lc);
    }
}

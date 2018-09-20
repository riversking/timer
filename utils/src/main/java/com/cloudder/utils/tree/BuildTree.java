package com.cloudder.utils.tree;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
@SuppressWarnings("all")
public class BuildTree{

    /**
     * 获取某个根节点下所有的子节点
     * @param oriTree 原始树
     * @param id 节点ID
     * @return
     */
    public static List<CommonTree> findTree(List<CommonTree> oriTree, Long id){
//        Iterator<Classify> iterator = classifyTree.iterator();
//        Classify classify = null;
//        Classify returnClassify = null;
//        List<Classify> classifies = null;
        for (CommonTree commonTree : oriTree) {
            List<CommonTree> commonTrees = commonTree.getChildren();
            if(id.equals(commonTree.getId()))
                return commonTrees;
            else if(commonTrees != null && !commonTrees.isEmpty()){
                List<CommonTree> returnCommonTrees = findTree(commonTrees, id);
                if(returnCommonTrees != null)
                    return returnCommonTrees;
            }
        }
        return null;
    }

    /**
     * 将通用树对象集合转换为树状结构
     * @param commonTrees 通用树集合
     * @param level 初始层级
     * @param maxLevel 最大层级
     * @return
     */

    public static List<? extends CommonTree> getTree(List<? extends CommonTree> commonTrees, int level, int maxLevel) {
        Boolean flag = true;
        List<CommonTree> tree = new ArrayList<>();
        if (commonTrees != null && !commonTrees.isEmpty()) {
            List<CommonTree> root = new ArrayList<>();
            if(maxLevel == 0) {
                maxLevel = getMaxLevel(commonTrees);
            }
            if(level == 0) {
                level = 1;
            }
            if (level >= maxLevel) {
                maxLevel = level;
            }
            while(flag){
                findOrgRoot(commonTrees, tree, level, root);
                level++;
                findOrgChild(commonTrees, level, root);
                if(level == maxLevel)
                    flag = false;
            }
        }
        return tree;
    }

    private static void findOrgRoot(List<? extends CommonTree> commonTrees, List<CommonTree> tree, Integer level, List<CommonTree> root){
        Iterator<? extends CommonTree> iterator = commonTrees.iterator();
        root.clear();
        while(iterator.hasNext()){
            CommonTree commonTree = iterator.next();
            long nodeLevel = commonTree.getLevel().longValue();
            if(nodeLevel == level.longValue()){
                if(nodeLevel == 1){
                    tree.add(commonTree);
                }
                commonTree.setChildren(new ArrayList<>());
                iterator.remove();
                root.add(commonTree);
            }
        }
    }

    private static void findOrgChild(List<? extends CommonTree> commonTrees, Integer level, List<CommonTree> root){
        for (CommonTree nodeR : root) {
            if (Integer.valueOf(nodeR.getLevel().intValue()).equals(level - 1)) {
                for (CommonTree nodeC : commonTrees) {
                    long nodeCLevel = nodeC.getLevel().longValue();
                    if (nodeCLevel == level.longValue() && nodeR.getId().equals(nodeC.getParentId())) {
                        if (nodeR.getChildren() == null) {
                            nodeR.setChildren(new ArrayList<>());
                        }
                        nodeR.getChildren().add(nodeC);
//                        nodeI.remove();
                    }
                }
            }
        }
    }



    private static int getMaxLevel(List<? extends CommonTree> commonTrees){
        int maxLevel = 0;
//        int nowLevel = 0;
        for (CommonTree commonTree : commonTrees) {
//            nowLevel = classify.getLevel();
            if(maxLevel <= commonTree.getLevel()){
                maxLevel = commonTree.getLevel();
            }
        }
        return maxLevel;
    }
}

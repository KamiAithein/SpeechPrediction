package main;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author Kenneth Koepcke
 *
 * Implementation of a basic tree storage system for ngrams using a beam search algorithm for lookup.
 */
public class BasicTreeNGRepo implements NGRepo<String, List<String>> {

    @NotNull
    Tree lexicalTree;

    /**
     * A lexical tree representing the likelihood of different phrases
     */
    static class Tree{
        /**
         * The root of a lexical tree
         */
        @NotNull
        private Node root;

        public Tree(@NotNull Node r){
            this.root = r;
        }

        /**
         * A node in a lexical tree representing a single word occurrence
         */
        abstract static class Node{
            @Nullable
            protected Node left;
            @Nullable
            protected Node right;
            /**
             * The word
             */
            @Nullable
            protected String data;
            @Nullable
            protected Node parent;

            static class NonRootNode extends Node{
                /**
                 * Constructor for non root nodes
                 * @param l the left node
                 * @param r the right node
                 * @param p the parent node
                 * @param data the word of this node
                 */
                public NonRootNode(@Nullable Node l,@Nullable Node r,@NotNull Node p, @NotNull String data){
                    super();
                    this.left = l;
                    this.right = r;
                    this.parent = p;
                    this.data = data;
                }
            }

            static class RootNode extends Node{
                /**
                 * Constructor for root nodes
                 * @param l the left node
                 * @param r the right node
                 */
                public RootNode(@Nullable Node l, @Nullable Node r){
                    this.left = l;
                    this.right = r;
                    this.parent = null;
                    this.data = null;
                }
            }
        }
    }

    @Override
    public void add(NGram data) {

    }

    @Override
    public void retrieve(BiConsumer<String, List<String>> filter) {

    }

    @Override
    public byte getLength() {
        return 0;
    }
}

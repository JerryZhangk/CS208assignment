package assignment9;

public class assignment9_Q2 {
    static room[] hotel;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int number = (int) (Math.log(n) / Math.log(2)) + 2;
        int length = (int) Math.pow(2, number) + 1;
        hotel = new room[length];
        merge(1, n, 1);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == 1) {
                int res = checkIn(b, 1);
                if (res != 0) {
                    update(res, res + b - 1, 1);
                }
                out.println(res);
            } else {
                int c = in.nextInt();
                checkOut(b, c, 1);
            }
        }
        out.close();
    }

    public static room merge(int l, int r, int index) {// init
        if (l == r) {
            hotel[index] = new room(l, r, index);
            hotel[index].lF = 1;
            hotel[index].rF = 1;
            hotel[index].max = 1;
            hotel[index].isLeaf = true;
            return hotel[index];
        }
        int mid = (l + r) / 2;
        room left = merge(l, mid, 2 * index);
        room right = merge(mid + 1, r, 2 * index + 1);
        hotel[index] = new room(l, r, index, left, right);
        hotel[index].lF = left.max + right.lF;// 开始时候不需要判断，因为最初时一定是全空的。
        hotel[index].rF = left.rF + right.max;
        return hotel[index];
    }

    public static void change(int index) {//根据当前节点更新父节点
        index /= 2;
        renew(index * 2);
        renew(index * 2 + 1);
        if (hotel[index * 2].lF != hotel[2 * index].r - hotel[2 * index].l + 1) {//表示不是全空的
            hotel[index].lF = hotel[index * 2].lF;
        } else {
            hotel[index].lF = hotel[index * 2].lF + hotel[index * 2 + 1].lF;
        }//lF

        if (hotel[index * 2 + 1].rF != hotel[2 * index + 1].r - hotel[2 * index + 1].l + 1) {//表示不是全空的
            hotel[index].rF = hotel[index * 2 + 1].rF;
        } else {
            hotel[index].rF = hotel[index * 2].rF + hotel[index * 2 + 1].rF;
        }//rF
        hotel[index].max = hotel[index].findMax(hotel[index * 2], hotel[index * 2 + 1]);
    }

    public static int checkIn(int number, int index) {
        int res = 0;
        if (hotel[index].max >= number) {// whether array out of bounds????
            renew(index * 2);
            renew(index * 2 + 1);
            if (hotel[2 * index].max >= number) {
                res = checkIn(number, 2 * index);
            } else if (hotel[2 * index].rF + hotel[2 * index + 1].lF >= number) {
                res = hotel[2 * index].r - hotel[2 * index].rF + 1;
            } else {
                res = checkIn(number, 2 * index + 1);
            }
        }
        return res;/// 0 表示没法check in
    }

    public static void update(int begin, int end, int index) {
        renew(index);
        if (hotel[index].l >= begin && hotel[index].r <= end) {// matching!
            hotel[index].lF = 0;// same as 0
            hotel[index].rF = 0;
            hotel[index].max = 0;
            hotel[index].isFree = false;
            hotel[index].isLazy = false;
            if (!hotel[index].isLeaf) {
                hotel[2 * index].isLazy = true;
                hotel[2 * index].isFree = false;
                hotel[2 * index + 1].isLazy = true;
                hotel[2 * index + 1].isFree = false;
            }
            while (index > 1) {
                change(index);
                index /= 2;
            }
            return;
        }
        if (hotel[2 * index].r < begin || hotel[2 * index].l > end) {//???会不会越界？
//            return;
        }
        if (!(hotel[2 * index].r < begin || hotel[2 * index].l > end)) {
            update(begin, end, 2 * index);
        }
        if (hotel[2 * index + 1].r < begin || hotel[2 * index + 1].l > end) {
//            return;
        }
        if (!(hotel[2 * index + 1].r < begin || hotel[2 * index + 1].l > end)) {
            update(begin, end, 2 * index + 1);
        }
    }

    public static void renew(int index) {
        int kk = hotel[index].r - hotel[index].l + 1;
        if (hotel[index].isFree) {
            hotel[index].lF = kk;
            hotel[index].rF = kk;
            hotel[index].max = kk;
            hotel[index].isLazy = false;
            hotel[index].isFree = false;
            if (!hotel[index].isLeaf) {
                hotel[2 * index].isFree = true;
                hotel[2 * index].isLazy = false;
                hotel[2 * index + 1].isFree = true;
                hotel[2 * index + 1].isLazy = false;
            }
        }
        if (hotel[index].isLazy) {
            hotel[index].lF = 0;
            hotel[index].rF = 0;
            hotel[index].max = 0;
            hotel[index].isLazy = false;
            hotel[index].isFree = false;
            //设置自己为全0 把儿子lazy lazy 自己false
            if (!hotel[index].isLeaf) {
                hotel[2 * index].isLazy = true;
                hotel[2 * index].isFree = false;
                hotel[2 * index + 1].isLazy = true;
                hotel[2 * index + 1].isFree = false;
            }
        }
    }

    public static void checkOut(int begin, int end, int index) {
        int kk = hotel[index].r - hotel[index].l + 1;
        renew(index);
        if (hotel[index].l >= begin && hotel[index].r <= end) {//matching! 区间是属于这个begin end 的所以说是完全释放
            hotel[index].isLazy = false;
            hotel[index].isFree = false;
            hotel[index].max = kk;
            hotel[index].lF = kk;
            hotel[index].rF = kk;
            if (!hotel[index].isLeaf) {// renew sons
                hotel[2 * index].isFree = true;
                hotel[2 * index].isLazy = false;
                hotel[2 * index + 1].isFree = true;
                hotel[2 * index + 1].isLazy = false;
            }
            while (index > 1) {//renew dads
                change(index);
                index /= 2;
            }
            return;
        }
        if (hotel[2 * index].r < begin || hotel[2 * index].l > end) {//???会不会越界？

        }
        if (!(hotel[2 * index].r < begin || hotel[2 * index].l > end)) {
            checkOut(begin, end, 2 * index);
        }
        if (hotel[2 * index + 1].r < begin || hotel[2 * index + 1].l > end) {

        }
        if (!(hotel[2 * index + 1].r < begin || hotel[2 * index + 1].l > end)) {
            checkOut(begin, end, 2 * index + 1);
        }
    }

}

class room {
    int l;
    int r;
    int lF;
    int rF;
    int max;
    int index;
    room leftChild;
    room rightChild;
    boolean isLazy;
    boolean isFree;
    boolean isLeaf;

    public room(int l, int r, int index) {
        this.l = l;
        this.r = r;
        this.index = index;
    }

    public room(int l, int r, int index, room leftChild, room rightChild) {//要么是叶子要么一定左右子树都有
        this.l = l;
        this.r = r;
        this.index = index;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.max = findMax(leftChild, rightChild);
    }

    public int findMax(room leftChild, room rightChild) {
        int length1 = leftChild.max;
        int length2 = rightChild.max;
        int length3 = leftChild.rF + rightChild.lF;
        int res = length1;
        if (res < length2) {
            res = length2;
        }
        if (res < length3) {
            res = length3;
        }
        return res;
    }
}


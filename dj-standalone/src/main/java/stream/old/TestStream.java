package stream.old;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-01-21 15:02
 **/
public class TestStream {
    List<Book> bookList = new ArrayList<>();

    @Before
    public void init() {
        bookList.add(Book.builder().author("天乔巴夏").id(1L).title("Java-Spring").pageCount(100).price(50d).build());
        bookList.add(Book.builder().author("summerday").id(2L).title("Java-SpringBoot").pageCount(200).price(100d).build());
        bookList.add(Book.builder().author("hyh").id(3L).title("mysql").pageCount(500).price(150d).build());
        bookList.add(Book.builder().author("tqbx").id(4L).title("Linux").pageCount(30).price(10d).build());

        System.out.println("总集合为：");
        for (Book bb:
        bookList) {
            System.out.println(bb);
        }

    }

    @Test
    public void boxedTest() {
//        int[]数组快速转化为List。

        int[] arr1 = {1, 2, 3, 4, 5};
        List<Integer> res1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        System.out.println(res1);

//        int[]数组逆序转化为List或Array。
        int[] arr = {1, 2, 3, 4, 5};
        int[] res = Arrays.stream(arr)
                .boxed() // 装箱
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i) // 转为IntStream
                .toArray();

        List<Integer> collect = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Integer[] r = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);

    }

    @Test
    public void streamTest() {
//        distinct、concat、limit、skip

        Stream<Integer> stream1 = Stream.of(1, 2, 2, 3, 4);
        Stream<Integer> stream2 = Stream.of(2, 3, 4, 5, 5);
        // 合并
        List<Integer> concatList = Stream.concat(stream1, stream2).collect(Collectors.toList());
        System.out.println(concatList);

        // 去重
        List<Integer> distinctList = concatList.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList);

        // 限制
        List<Integer> limitList = distinctList.stream().limit(3).collect(Collectors.toList());
        System.out.println(limitList);

        // 跳过
        List<Integer> skipList = limitList.stream().skip(1).collect(Collectors.toList());
        System.out.println(skipList);

        // 迭代
        List<Integer> iterateList = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        System.out.println(iterateList);

        // 生成
        List<Integer> generateList = Stream.generate(() -> new Random().nextInt()).limit(5).collect(Collectors.toList());
        System.out.println(generateList);
    }

    @Test
    public void sortTest() {
        // 按价格升序
        List<Book> sortListByPrice = bookList.stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toList());
        System.out.println(sortListByPrice);
        // 按价格降序
        List<Book> sortListByPriceReversed = bookList.stream().sorted(Comparator.comparing(Book::getPrice).reversed()).collect(Collectors.toList());
        System.out.println(sortListByPriceReversed);

        // 先价格再页数
        List<Book> sortListByPriceAndPageCount = bookList.stream().sorted(Comparator.comparing(Book::getPrice)
                .thenComparing(Book::getPageCount)).collect(Collectors.toList());
        System.out.println(sortListByPriceAndPageCount);
    }


    @Test
    public void joinTest() {
        // 获取所有书名
        String titles = bookList.stream().map(Book::getTitle).collect(Collectors.joining(","));
        System.out.println("所有书名 : " + titles);
    }

    @Test
    public void groupTest() {
        // 按书的价格是否高于100分组
        Map<Boolean, List<Book>> part = bookList.stream().collect(Collectors.partitioningBy(book -> book.getPrice() > 100));

        for (Map.Entry<Boolean, List<Book>> entry : part.entrySet()) {
            if (entry.getKey().equals(Boolean.TRUE)) {
                System.out.println("price > 100 ==> " + entry.getValue());
            } else {
                System.out.println("price <= 100 <== " + entry.getValue());
            }
        }

        // 按页数分组
        Map<Integer, List<Book>> group = bookList.stream().collect(Collectors.groupingBy(Book::getPageCount));
        System.out.println(group);
    }

    @Test
    public void numberDealTest() {
//        count、averaging、summarizing、max、sum

        // 统计书籍总数
        Long bookCount = bookList.stream().filter(book -> "天乔巴夏".equals(book.getAuthor())).count();

        // 求平均价格
        Double average = bookList.stream().collect(Collectors.averagingDouble(Book::getPrice));

        // 求最贵价格
        Optional<Integer> max = bookList.stream().map(Book::getPageCount).max(Double::compare);

        // 求价格之和
        Integer priceCount = bookList.stream().mapToInt(Book::getPageCount).sum();

        // 一次性统计所有信息
        DoubleSummaryStatistics c = bookList.stream().collect(Collectors.summarizingDouble(Book::getPrice));

    }
    @Test
    public void collectTest() {
        // 将所有书籍存入 author -> title 的map中
        Map<String, String> map = bookList.stream().collect(Collectors.toMap(Book::getAuthor, Book::getTitle));
        // 取出所有id为偶数的书,存入list
        List<Book> list = bookList.stream().filter(book -> book.getId() % 2 == 0).collect(Collectors.toList());
        // 取出所有标题长度大于5的书,存入list
        Set<Book> set = bookList.stream().filter(book -> book.getTitle().length() > 5).collect(Collectors.toSet());
    }

    @Test
    public void reduceTest() {
        // 求所有书籍的页数之和
        Integer totalPageCount = bookList.stream().reduce(0, (s, book) -> s += book.getPageCount(), Integer::sum);
        System.out.println("所有书籍的页数之和 : " + totalPageCount);
    }

    @Test
    public void peakAndMap() {
        // 将所有的书的价格调高100并输出调高以后的书单
        List<Book> result = bookList.stream().peek(book -> book.setPrice(book.getPrice() + 100))
                .collect(Collectors.toList());
        result.forEach(System.out::println);
        // 获取所有书的id列表
        List<Long> ids = bookList.stream().map(Book::getId).collect(Collectors.toList());
        System.out.println(ids);
    }
    @Test
    public void maxAndCountMatch() {
        // 获取页数最多的书
        Optional<Book> max = bookList.stream().max(Comparator.comparingInt(Book::getPageCount));
        max.ifPresent(book -> System.out.println("页数最多的书 : " + book));

        // 计算mysql书籍有几本
        long count = bookList.stream().filter(book -> book.getTitle().contains("mysql")).count();
        System.out.println("mysql书籍的本数 : " + count);
    }
    @Test
    public void filterMatch() {
//      找到所有id为奇数的书,列出他们的书名到list中
        List<String> titleList = bookList.stream()
                .filter(book -> book.getId() % 2 == 1)
                .map(Book::getTitle)
                .collect(Collectors.toList());
        System.out.println(titleList);
    }


    @Test
    public void findMatch() {
        // 是否包含符合条件的书
        boolean anyMatch = bookList.stream().anyMatch(book -> book.getPageCount() > 100);
        System.out.println("是否存在页数大于100的书 : " + anyMatch);

        // 检查是否有名字长度大于5 的
        boolean noneMatch = bookList.stream().noneMatch(book -> (book.getTitle().length() > 5));
        System.out.println("不存在title长度大于5的书 : " + noneMatch);
    }

    @Test
    public void filterTest() {
        // 匹配第一个
        Optional<Book> first = bookList.stream().filter(book -> book.getPageCount() > 1).findFirst();
        first.ifPresent(book -> System.out.println("匹配第一个值 : " + book));
        // 匹配任意
        Optional<Book> any = bookList.parallelStream().filter(book -> book.getPageCount() > 1).findAny();
        any.ifPresent(book -> System.out.println("匹配任意的值 : " + book));
    }






}

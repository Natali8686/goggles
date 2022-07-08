package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exeption.AlreadyExistsException;
import ru.netology.exeption.NotFoundException;

import java.rmi.AlreadyBoundException;

public class ProductRepository {

    private Product[] products = new Product[0];

    public void add(Product product) {
        if (findById(product.getId()) == null) {
            int length = products.length + 1;
            Product[] tmp = new Product[length];
            for (int i = 0; i < products.length; i++) {
                tmp[i] = products[i];
            }
            tmp[tmp.length - 1] = product;
            products = tmp;
        } else {
            throw new AlreadyExistsException("Element with id: " + product + " already exists");
        }
    }

    public Product[] findAll() {
        return products;
    }

    public Product[] findById(int id) {// findById,предназначен для поиска товара в репозитории по его id.Так, он должен принимать параметром id искомого товара, пробегаться по всем товарам репозитория и сверять их id с искомым
        for (Product product : products) {
            if (product.getId() == id) { // в случае совпадения делает return этого товара.Если же ни один подходящий id найден не был (т.е. цикл закончился без вызова return внутри него), то сделать return null
                return new Product[]{product};
            }
        }
        return null;
    }

    public void removeById(int id) { // вызываем метод findById:если результат - null,то выкидываем исключение NotFoundException
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");// throw - кинь
        }
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }
}



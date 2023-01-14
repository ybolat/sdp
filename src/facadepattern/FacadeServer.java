package facadepattern;

import module.*;
import singleton.SingleObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FacadeServer {

    public void main() throws SQLException {
        Scanner in = new Scanner(System.in);

        SingleObject object = SingleObject.getInstance();
        object.connect();

        ResultSet rs = null;

        boolean inSystem = true;

        while (inSystem) {
            int choice;

            while (true) {
                System.out.println("1. Войти");
                System.out.println("2. Регистрация");

                System.out.print("Введите цифру: ");
                choice = in.nextInt();

                if (choice != 1 && choice != 2) {
                    System.out.println("Выберите 1 или 2");
                } else {
                    break;
                }
            }

            String email;
            String password;
            if (choice == 1) {
                User mainUser = null;

                while (true) {
                    System.out.print("Введите почту: ");
                    email = in.next();

                    System.out.print("Введите пароль: ");
                    password = in.next();

                    rs = object.getStmt().executeQuery("SELECT * FROM users");

                    List<User> userList = new ArrayList<>();
                    HashMap<Integer, Integer> roleIdList = new HashMap<>();

                    while (rs.next()) {
                        User user = new User();

                        user.setId(rs.getLong("id"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        roleIdList.put(userList.size(), rs.getInt("role_id"));
                        userList.add(user);
                    }

                    for (Map.Entry<Integer, Integer> set :
                            roleIdList.entrySet()) {

                        rs = object.getStmt().executeQuery("SELECT * FROM role where id=" + set.getValue());

                        while (rs.next()) {
                            Role role = new Role();

                            role.setId(rs.getLong("id"));
                            role.setRole(rs.getString("role"));
                            userList.get(set.getKey()).setRole(role);
                        }
                    }

                    for (User u : userList) {
                        if (u.getPassword().equals(password) && u.getEmail().equals(email)) {
                            mainUser = u;
                            break;
                        }
                    }

                    if (mainUser == null) {
                        System.out.println("Такого пользователя не существует");
                    } else {
                        break;
                    }
                }

                if (mainUser.getRole().getRole().equals("ROLE_USER")) {
                    while (true) {
                        System.out.println("1. Заказать");
                        System.out.println("2. Мои заказы");
                        System.out.println("3. Выйти");

                        System.out.print("Введите цифру: ");
                        choice = in.nextInt();

                        if (choice == 1) {
                            rs = object.getStmt().executeQuery("SELECT * FROM store");

                            List<Store> storeList = new ArrayList<>();

                            while (rs.next()) {
                                Store store = new Store();

                                store.setId(rs.getLong("id"));
                                store.setFlowerName(rs.getString("flowers_name"));
                                store.setPrice(rs.getInt("price"));

                                storeList.add(store);
                            }

                            storeList.forEach(v -> {
                                System.out.println(v.getId() + " " + v.getFlowerName() + " " + v.getPrice());
                            });

                            System.out.print("Введите цифру: ");
                            choice = in.nextInt();

                            int totalPrice = storeList.get(choice - 1).getPrice() + 500;

                            String sql = "Insert into orders (status, " +
                                    "user_profile_id, total_price, store_id)"
                                    + " values ('заказ оформлен'," + mainUser.getId() + ", " +
                                    totalPrice + ", " + choice + ")";

                            object.getStmt().executeUpdate(sql);
                        } else if (choice == 2) {
                            rs = object.getStmt().executeQuery("Select * from orders where user_profile_id=" + mainUser.getId());

                            List<Order> orderList = new ArrayList<>();
                            HashMap<Integer, Integer> storeIdList = new HashMap<>();
                            HashMap<Integer, Integer> courierIdList = new HashMap<>();

                            while (rs.next()) {
                                Order order = new Order();

                                order.setId(rs.getLong("id"));
                                order.setStatus(rs.getString("status"));
                                order.setTotal_price(rs.getInt("total_price"));

                                storeIdList.put(orderList.size(), rs.getInt("store_id"));
                                courierIdList.put(orderList.size(), rs.getInt("courier_id"));

                                orderList.add(order);
                            }

                            for (Map.Entry<Integer, Integer> set :
                                    storeIdList.entrySet()) {
                                rs = object.getStmt().executeQuery("select * from store where id=" + set.getValue());

                                while (rs.next()) {
                                    Store store = new Store();

                                    store.setId(rs.getLong("id"));
                                    store.setPrice(rs.getInt("price"));
                                    store.setFlowerName(rs.getString("flowers_name"));

                                    orderList.get(set.getKey()).setStore(store);
                                }
                            }

                            for (Map.Entry<Integer, Integer> set :
                                    courierIdList.entrySet()) {
                                rs = object.getStmt().executeQuery("select * from courier where id=" + set.getValue());

                                while (rs.next()) {
                                    Courier courier = new Courier();

                                    courier.setId(rs.getLong("id"));
                                    courier.setPhone(rs.getString("phone"));

                                    orderList.get(set.getKey()).setCourier(courier);
                                }

                            }

                            orderList.forEach(v -> {
                                System.out.println("Номер заказа: " + v.getId());
                                System.out.println("Статус: " + v.getStatus());
                                System.out.println("Цена: " + v.getTotal_price());
                                System.out.println("Цветы: " + v.getStore().getFlowerName());
                                if (v.getCourier() != null)
                                    System.out.println("Свзят с курьером: " + v.getCourier().getPhone());
                                System.out.println("-----------------------------------------------");
                            });

                        } else {
                            inSystem = false;
                            break;
                        }
                    }
                } else if (mainUser.getRole().getRole().equals("ROLE_COURIER")) {
                    while (true) {
                        System.out.println("1. Заказы");
                        System.out.println("2. Выход");

                        System.out.print("Введите цифру: ");
                        choice = in.nextInt();

                        if (choice == 1) {
                            rs = object.getStmt().executeQuery("Select * from orders where courier_id is null");

                            List<Order> orderList = new ArrayList<>();
                            HashMap<Integer, Integer> storeIdList = new HashMap<>();

                            while (rs.next()) {
                                Order order = new Order();

                                order.setId(rs.getLong("id"));
                                order.setStatus(rs.getString("status"));
                                order.setTotal_price(rs.getInt("total_price"));

                                storeIdList.put(orderList.size(), rs.getInt("store_id"));

                                orderList.add(order);
                            }

                            for (Map.Entry<Integer, Integer> set :
                                    storeIdList.entrySet()) {
                                rs = object.getStmt().executeQuery("select * from store where id=" + set.getValue());

                                while (rs.next()) {
                                    Store store = new Store();

                                    store.setId(rs.getLong("id"));
                                    store.setPrice(rs.getInt("price"));
                                    store.setFlowerName(rs.getString("flowers_name"));

                                    orderList.get(set.getKey()).setStore(store);
                                }
                            }

                            orderList.forEach(v -> {
                                System.out.println("Номер заказа: " + v.getId());
                                System.out.println("Статус: " + v.getStatus());
                                System.out.println("Цена: " + v.getTotal_price());
                                System.out.println("Цветы: " + v.getStore().getFlowerName());
                                System.out.println("-----------------------------------------------");
                            });

                            System.out.print("Введите номер заказа: ");
                            choice = in.nextInt();

                            object.getStmt().executeUpdate("update orders set courier_id=" + mainUser.getId() + " and status='доставка' where id=" + choice);
                        } else {
                            inSystem = false;
                            break;
                        }
                    }
                } else if (mainUser.getRole().getRole().equals("ROLE_FLORIST")) {
                    while (true) {
                        System.out.println("1. Заказы");
                        System.out.println("2. В работе");
                        System.out.println("3. Выход");

                        System.out.print("Введите цифру: ");
                        choice = in.nextInt();

                        if (choice == 1) {
                            rs = object.getStmt().executeQuery("Select * from orders where status='заказ оформлен'");

                            List<Order> orderList = new ArrayList<>();
                            HashMap<Integer, Integer> storeIdList = new HashMap<>();

                            while (rs.next()) {
                                Order order = new Order();

                                order.setId(rs.getLong("id"));
                                order.setStatus(rs.getString("status"));
                                order.setTotal_price(rs.getInt("total_price"));

                                storeIdList.put(orderList.size(), rs.getInt("store_id"));

                                orderList.add(order);
                            }

                            for (Map.Entry<Integer, Integer> set :
                                    storeIdList.entrySet()) {
                                rs = object.getStmt().executeQuery("select * from store where id=" + set.getValue());

                                while (rs.next()) {
                                    Store store = new Store();

                                    store.setId(rs.getLong("id"));
                                    store.setPrice(rs.getInt("price"));
                                    store.setFlowerName(rs.getString("flowers_name"));

                                    orderList.get(set.getKey()).setStore(store);
                                }
                            }

                            orderList.forEach(v -> {
                                System.out.println("Номер заказа: " + v.getId());
                                System.out.println("Статус: " + v.getStatus());
                                System.out.println("Цена: " + v.getTotal_price());
                                System.out.println("Цветы: " + v.getStore().getFlowerName());
                                System.out.println("-----------------------------------------------");
                            });

                            System.out.print("Введите номер заказа: ");
                            choice = in.nextInt();

                            object.getStmt().executeUpdate("update orders set status='в работе' where id=" + choice);
                        } else if (choice == 2) {
                            rs = object.getStmt().executeQuery("Select * from orders where status='в работе'");

                            List<Order> orderList = new ArrayList<>();
                            HashMap<Integer, Integer> storeIdList = new HashMap<>();

                            while (rs.next()) {
                                Order order = new Order();

                                order.setId(rs.getLong("id"));
                                order.setStatus(rs.getString("status"));
                                order.setTotal_price(rs.getInt("total_price"));

                                storeIdList.put(orderList.size(), rs.getInt("store_id"));

                                orderList.add(order);
                            }

                            for (Map.Entry<Integer, Integer> set :
                                    storeIdList.entrySet()) {
                                rs = object.getStmt().executeQuery("select * from store where id=" + set.getValue());

                                while (rs.next()) {
                                    Store store = new Store();

                                    store.setId(rs.getLong("id"));
                                    store.setPrice(rs.getInt("price"));
                                    store.setFlowerName(rs.getString("flowers_name"));

                                    orderList.get(set.getKey()).setStore(store);
                                }
                            }

                            orderList.forEach(v -> {
                                System.out.println("Номер заказа: " + v.getId());
                                System.out.println("Статус: " + v.getStatus());
                                System.out.println("Цена: " + v.getTotal_price());
                                System.out.println("Цветы: " + v.getStore().getFlowerName());
                                System.out.println("-----------------------------------------------");
                            });

                            System.out.print("Введите номер заказа: ");
                            choice = in.nextInt();

                            object.getStmt().executeUpdate("update orders set status='закончен' where id=" + choice);
                        } else {
                            inSystem = false;
                            break;
                        }
                    }
                }
            }

        }

        rs.close();
        object.getStmt().close();
        object.getConn().close();

    }

}

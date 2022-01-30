package program;

import models.Category;
import models.Commodity;
import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HyperContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Session context = HyperContext.getSessionFactory().openSession();
        System.out.println("Successful connect");
//        System.out.print("Enter name of role: ");
//        String name = in.nextLine();
//        Role r = new Role();
//        r.setName(name);
//        context.save(r);
//        Query query = context.createQuery("FROM Role");
//        List<Role> roles = query.list();
//
//        for (Role r : roles) {
//            System.out.println(r);
//        }


        int menu = 0;

        System.out.print("Select table: 1-Category 2-Commodity: ");
        menu = in.nextInt();

        switch (menu) {
            case 1:
                while (menu != -1) {
                    System.out.println("------------------------------------------\n" +
                            "1 - Select from DB\n" +
                            "2 - Insert into DB\n" +
                            "3 - Delete from DB\n" +
                            "4 - Update colum in DB\n" +
                            "\n" +
                            "-1 - Exit\n" +
                            "------------------------------------------");
                    menu = in.nextInt();

                    switch (menu)
                    {
                        case 1:
                            SelectCategory();
                            break;
                        case 2:
                            InsertCategory();
                            break;
                        case 3:
                            DeleteCategory();
                            break;
                        case 4:
                            UpdateCategory();
                            break;
                        case -1:
                            return;
                    }
                }
                break;
            case 2:
                while (menu != -1) {
                    System.out.println("------------------------------------------\n" +
                            "1 - Select from DB\n" +
                            "2 - Insert into DB\n" +
                            "3 - Delete from DB\n" +
                            "4 - Update colum in DB\n" +
                            "\n" +
                            "-1 - Exit\n" +
                            "------------------------------------------");
                    menu = in.nextInt();

                    switch (menu)
                    {
                        case 1:
                            SelectCommodity();
                            break;
                        case 2:
                            InsertCommodity();
                            break;
                        case 3:
                            DeleteCommodity();
                            break;
                        case 4:
                            UpdateCommodity();
                            break;
                        case -1:
                            return;
                    }
                }
                break;
            default:
                System.out.println("invalid select");
                return;
        }

        context.close();
    }

    static private void SelectCategory() {
        try {
            Session context = HyperContext.getSessionFactory().openSession();
            Query query = context.createQuery("FROM Category");
            List<Category> categories = query.list();

            for (Category c : categories) {
                System.out.println(c.toString());
            }
            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void InsertCategory() {
        try {
            Session context = HyperContext.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();

            Category c = new Category();
            System.out.print("Enter title for new  category: ");
            in.nextLine();
            c.setTitle(in.nextLine());

            context.save(c);
            tx.commit();
            System.out.println("Successful insert");

            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void DeleteCategory() {
        try {
            SelectCategory();

            Session context = HyperContext.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();



            //Category c = new Category();
            System.out.print("Enter id for del: ");
            //in.nextLine();
            int i = in.nextInt();

            Category category = context.get(Category.class, i);
            Query q = context.createQuery("delete from  Commodity where category = :c");
            q.setParameter("c", category);
            q.executeUpdate();
            //tx.commit();

            context.delete(category);
            tx.commit();


//        Query query = context.createQuery("delete from Category where id = :Id");
//
//        query.setParameter("Id", i);
//
//        query.executeUpdate();

            System.out.println("Successful del");
            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void UpdateCategory() {
        try {
            SelectCategory();

            Session context = HyperContext.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();

            System.out.print("Enter id for update: ");
            int i = in.nextInt();

            Category category = context.get(Category.class, i);
            System.out.println("Enter new title: ");
            in.nextLine();
            category.setTitle(in.nextLine());

            context.update(category);
            tx.commit();

            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void SelectCommodity() {
        try {
            Session context = HyperContext.getSessionFactory().openSession();
            Query query = context.createQuery("FROM Commodity");
            List<Commodity> commodities = query.list();

            for (Commodity c : commodities) {
                System.out.println(c.toString());
            }
            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void InsertCommodity() {
        try {
            Session context = HyperContext.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();

            Commodity c = new Commodity();
            System.out.print("Enter title for new commodity: ");
            in.nextLine();
            c.setTitle(in.nextLine());

            SelectCategory();
            System.out.print("Select id of category: ");
            int i = in.nextInt();
            Category cat = context.get(Category.class, i);
            c.setCategory(cat);
            context.save(c);
            tx.commit();
            System.out.println("Successful insert");

            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void DeleteCommodity() {
        try {
            SelectCommodity();

            Session context = HyperContext.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();



            //Category c = new Category();
            System.out.print("Enter id for del: ");
            //in.nextLine();
            int i = in.nextInt();

            Commodity commodity = context.get(Commodity.class, i);

            context.delete(commodity);
            tx.commit();

            System.out.println("Successful del");
            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static private void UpdateCommodity() {
        try {
            SelectCommodity();

            Session context = HyperContext.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();

            System.out.print("Enter id for update: ");
            int i = in.nextInt();

            Commodity commodity = context.get(Commodity.class, i);
            System.out.println("Enter new title: ");
            in.nextLine();
            commodity.setTitle(in.nextLine());

            context.update(commodity);
            tx.commit();

            context.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

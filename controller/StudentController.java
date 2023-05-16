package qlsv_swing.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import qlsv_swing.qlsv.entity.Customer;
import qlsv_swing.qlsv.entity.Vaccine;
import qlsv_swing.qlsv.services.CustomerService;
import qlsv_swing.qlsv.view.CustomerView;

public class StudentController {
    private CustomerService studentDao;
    private CustomerView customerView;

    public StudentController(CustomerView view) {
        this.customerView = view;
        studentDao = new CustomerService();

        view.addAddStudentListener(new AddStudentListener());
        view.searchCustomerListener(new searchCustomerListener());
        view.showAllCustomerListener(new showAllCustomerListener());
        view.addAddVaccineListener(new AddVaccineListener());

        view.addEdiStudentListener(new EditStudentListener());
        view.addEdiVaccineListener(new EditVaccineListener());

        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addDeleteVaccineListener(new DeleteVaccineListener());

        view.addClearListener(new ClearStudentListener());
        view.addClearVaccineListener(new ClearVaccineListener());

        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
        view.addListVaccineSelectionListener(new ListVaccineSelectionListener());
    }

    public void showStudentView() {
        List<Customer> customerList = studentDao.getListStudents();
        customerView.setVisible(true);
        customerView.showListCustomers(customerList);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.add(customer);
                customerView.showStudent(customer);
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Thêm thành công!");
            }
        }
    }

    class searchCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = customerView.getNameSearch();
            if (name != null) {
                customerView.showListCustomers(studentDao.findAllByName(name));
            } else {
                customerView.showMessage("chưa nhập name search!");
            }
        }
    }

    class showAllCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.showListCustomers(studentDao.getListStudents());

        }
    }

    class AddVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine vaccine = customerView.getVaccineInfo();
            int idCustomer = customerView.getIdCustomer();
            if (vaccine != null && idCustomer != -1) {
                studentDao.addVaccine(idCustomer, vaccine);
//                customerView.showStudent(customer);
                customerView.showListVaccine(studentDao.findById(idCustomer).getVaccines());
                customerView.showMessage("Thêm Vaccine thành công!");
            } else {
                customerView.showMessage("Không thể thêm vì chưa biết thêm cho customer nào!");

            }
        }
    }

    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.edit(customer);
                customerView.showStudent(customer);
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class EditVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine vaccine = customerView.getVaccineInfo();
            int idCustomer = customerView.getIdCustomer();
            if (vaccine != null && idCustomer != -1) {
                studentDao.editVaccine(idCustomer, vaccine);
                customerView.showListVaccine(studentDao.findById(idCustomer).getVaccines());
                customerView.showMessage("Cập nhật thành công!");
            } else {
                if (idCustomer == -1) customerView.showMessage("Không thể sửa vì chưa biết sửa cho customer nào!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.delete(customer);
                customerView.clearStudentInfo();
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Xóa thành công!");
            }
        }
    }

    class DeleteVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine vaccine = customerView.getVaccineInfo();
            int idCustomer = customerView.getIdCustomer();
            if (vaccine != null && idCustomer != -1) {
                studentDao.deleteVaccine(vaccine.getId());
                customerView.clearVaccineInfo();
                customerView.showListVaccine(studentDao.findById(idCustomer).getVaccines());
                customerView.showMessage("Xóa thành công!");
            } else {
                customerView.showMessage("Không thể xóa vì chưa biết xóa cho customer nào!");
            }
        }
    }


    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearStudentInfo();
        }
    }

    class ClearVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearVaccineInfo();
        }
    }


    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortCustomerByName();
            customerView.showListCustomers(studentDao.getListStudents());
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillCustomerFromSelectedRow();
        }
    }

    class ListVaccineSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillVaccineFromSelectedRow();
        }
    }
}
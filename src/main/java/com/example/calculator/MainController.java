package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label result;

    @FXML
    private Label prevnum;

    @FXML
    private Button clear;

    private String operator = "";

    private Double total = 0.0;

    private boolean check = true;

    private Calculations calculations = new Calculations();

    public void number(String number) {
        result.setText(result.getText() + number);
    }

    public void prevNumber(String number) {
        prevnum.setText(prevnum.getText() + number);
    }

    public void prevOperator(String operator) {
        prevnum.setText(prevnum.getText() + " " + operator + " ");
    }

    @FXML
    public void computerProcess(ActionEvent event) {
        if (check) {
            result.setText("");
            prevnum.setText("");
            check = false;
        }
        Button button = (Button)event.getSource();
        String value = button.getText();
        number(value);
        prevNumber(value);
    }

    @FXML
    public void operatorProcess(ActionEvent event) {

        try {
            Button button = (Button)event.getSource();
            String value = button.getText();

            if (!value.equals("=")) {

                // единственный унарный оператор, отдельно обрабатываем этот случай
                if (value.equals("√")) {
                    operator = value;
                    calculations.setNum1(Double.parseDouble(result.getText()));
                    total = calculations.create(operator);
                    result.setText(String.valueOf(total));
                    prevnum.setText(String.valueOf(total));

                    operator = "";
                    check = true;
                    return;
                }

                if (!operator.isEmpty()) {
                    return;
                }
                operator = value;
                prevOperator(operator);
                calculations.setNum1(Double.parseDouble(result.getText()));
                result.setText("");

            } else {

                if (operator.isEmpty()) {
                    return;
                }

                calculations.setNum2(Double.parseDouble(result.getText()));
                total = calculations.create(operator);
                result.setText(String.valueOf(total));

                operator = "";
                check = true;
            }

        } catch (ArithmeticException e) {
            prevnum.setText("");
            result.setText(e.getMessage());
            operator = "";
            check = true;

        }
    }

    public void clear(ActionEvent event) {
        if (event.getSource() == clear) {
            result.setText("");
            prevnum.setText("");
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void changeSign() {
        double n = Double.parseDouble(result.getText());
        n = -n;
        result.setText(String.valueOf(n));

        if (operator.isEmpty()) {
            prevnum.setText(String.valueOf(n));
            calculations.setNum1(n);
        } else {
            prevnum.setText(calculations.getNum1() + " " + operator + " " + n);
            calculations.setNum2(n);
        }
    }

    public void backspaceButton() {
        try {
            // удалить можно только те числа, что находятся в Label result, то есть операторы нельзя удалить таким образом
            String tempResult = result.getText();
            if (tempResult.length() == 0) {
                return;
            }
            if (tempResult.length() == 1) {
                result.setText("");
                throw new StringIndexOutOfBoundsException();
            }
            tempResult = tempResult.substring(0, tempResult.length() - 1);
            result.setText(tempResult);

            if (operator.isEmpty()) {
                calculations.setNum1(Double.parseDouble(tempResult));
                prevnum.setText(tempResult);
            } else {
                calculations.setNum2(Double.parseDouble(tempResult));
                prevnum.setText(calculations.getNum1() + " " + operator + " " + tempResult);
            }
        } catch (StringIndexOutOfBoundsException e) {
            if (operator.isEmpty()) {
                calculations.setNum1(0);
                prevnum.setText("");
            } else {
                calculations.setNum2(0);
                prevnum.setText(calculations.getNum1() + " " + operator);
            }
        }
    }

    public void convertHeightIntoSize() {
        try {
            if (!result.getText().equals(prevnum.getText())) {
                throw new NumberFormatException();
            }
            int height = Integer.valueOf(result.getText());
            if (height < 150 || height > 188) {
                throw new IllegalArgumentException();
            }
            String size = Calculations.getStringSizeUsingHeight(height);
            int[] sizes = Calculations.getNumberSizes(size);
            result.setText("Размер: " + size + "(" + sizes[0] + " - " + sizes[1] + ")");
            prevnum.setText("");
        } catch (NumberFormatException e) {
            prevnum.setText("");
            result.setText("Ошибка в вводе данных");
            operator = "";
            check = true;
        } catch (IllegalArgumentException e) {
            prevnum.setText("");
            result.setText("Диапазон роста: 158 - 188");
            check = true;
        }
    }

    public void convertSizeIntoHeight(ActionEvent event) {
        Button button = (Button)event.getSource();
        String size = button.getText();
        int[] heights = Calculations.getHeight(size);
        int[] numberSizes = Calculations.getNumberSizes(size);
        prevnum.setText("");
        result.setText("Размер:" + numberSizes[0] + " - " + numberSizes[1] + " Рост:" + heights[0] + " - " + heights[1]);
    }

    public void convertNumberSizeIntoHeight() {
        try {
            if (!result.getText().equals(prevnum.getText()) || !operator.isEmpty()) {
                throw new NumberFormatException();
            }
            int numberSize = Integer.parseInt(result.getText());
            if (numberSize < 44 || numberSize > 58) {
                throw new IllegalArgumentException();
            }
            String stringSize = Calculations.getStringSizeUsingNumberSize(numberSize);
            int[] heights = Calculations.getHeight(stringSize);
            prevnum.setText("");
            result.setText("Размер:" + stringSize + " " + "Рост:" + heights[0] + " - " + heights[1]);
            check = true;
        } catch (NumberFormatException e) {
            prevnum.setText("");
            result.setText("Ошибка в вводе данных");
            operator = "";
            check = true;
        } catch (IllegalArgumentException e) {
            prevnum.setText("");
            result.setText("Диапазон размеров: 44 - 58");
            check = true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
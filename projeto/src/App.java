import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage; 
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class App extends Application { 
    public static void main(String[] args) { 
        launch(args); 
    } 

    @Override 
    public void start(Stage primaryStage) {
        //STYLE 
        GridPane grid = new GridPane();
        TextArea txtResultado = new TextArea();
        ImageView imagem = new ImageView();

        
        Label titulo = new Label("CALCULADORA DE CUSTOS - IMPRESSÃO 3D");
        Button btnCalcular = new Button("CALCULAR");
        BorderPane root = new BorderPane();
        titulo.setMaxWidth(Double.MAX_VALUE);

        // Mencionando o style 
        grid.setStyle(Style.GRID);
        btnCalcular.setStyle(Style.BUTTON_PRIMARY);
        txtResultado.setStyle(Style.TEXTAREA);
        titulo.setStyle(Style.TITLE);
        root.setStyle(Style.ROOT);

        // Linhas e colunas 
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        btnCalcular.setPrefWidth(120);

        txtResultado.setEditable(false);
        txtResultado.setWrapText(true);
        txtResultado.setPrefWidth(850);
        txtResultado.setPrefHeight(900);

        // Tamanho das imagens
        imagem.setFitWidth(400);
        imagem.setFitHeight(400);
        imagem.setPreserveRatio(true);

        // Label para selecionar o modelo desejado 
        Label lblModelo = new Label("Modelo da Impressora:");

        ComboBox<String> comboModelo = new ComboBox<>();
        comboModelo.getItems().addAll(
            "Ender 3",
            "Creality K1",
            "Bambu Lab A1"
        );

        comboModelo.setPromptText("Selecione uma impressora");

        // Caminho das imagens
        Image bambu = new Image(getClass().getResourceAsStream("/img/Bambu_Lab_A1.jpg"));
        Image creality = new Image(getClass().getResourceAsStream("/img/Creality_K1.jpg"));
        Image ender = new Image(getClass().getResourceAsStream("/img/Ender_3.jpg"));

        // Caso selecionado um modelo, a imagem da impressora aparece
        comboModelo.setOnAction(e -> {
            String modelo = comboModelo.getValue();

            if(modelo.equals("Ender 3")){
                imagem.setImage(ender);
            }
            if(modelo.equals("Creality K1")){
                imagem.setImage(creality);
            }
            if(modelo.equals("Bambu Lab A1")){
                imagem.setImage(bambu);
            }
        });

        // Preencher com as informações da Impressora
        Label lblArquivo = new Label("Nome do Arquivo:");
        TextField txtArquivo = new TextField();

        Label lblDescricao = new Label("Descrição:");
        TextField txtDescricao = new TextField();

        Label lblTempo = new Label("Tempo de Impressão:");
        TextField txtTempo = new TextField();

        Label lblQuantidade = new Label("Quantidade Material:");
        TextField txtMaterial = new TextField();

        // MATERIAL
        Label lblTipoMaterial = new Label("Tipo Material:");

        ComboBox<String> comboMaterial = new ComboBox<>();
        comboMaterial.getItems().addAll(
            "PLA baixa densidade",
            "PLA média densidade",
            "PLA alta densidade"
        );

        comboMaterial.setPromptText("Selecione o material");
        btnCalcular.setOnAction(e -> {
            // IMPRESSORA OBJETO
            Impressora3D impressora =new Impressora3D();
            impressora.setModelo(comboModelo.getValue());

            if (comboModelo.getValue().equals("Ender 3")) {
                impressora.setPreco(1500);
                impressora.setPotencia(350);
            }
            if (comboModelo.getValue().equals("Creality K1")) {
                impressora.setPreco(3500);
                impressora.setPotencia(500);
            }
            if (comboModelo.getValue().equals("Bambu Lab A1")) {
                impressora.setPreco(4200);
                impressora.setPotencia(400);
            }

            // MATERIAL OBJETO
            MaterialImpresso material = new MaterialImpresso();
            material.setMaterial(comboMaterial.getValue());

            if (comboMaterial.getValue().equals("PLA baixa densidade")) {
                material.setDensidade(1);
                material.setC_grama(0.08);
            }
            if (comboMaterial.getValue().equals("PLA média densidade")) {
                material.setDensidade(2);
                material.setC_grama(0.12);
            }
            if (comboMaterial.getValue().equals("PLA alta densidade")) {
                material.setDensidade(3);
                material.setC_grama(0.18);
            }

        // PROJETO OBJETO
        ProjetoImpresso projeto = new ProjetoImpresso();

        projeto.setNomeArquivo(txtArquivo.getText());
        projeto.setDescricao(txtDescricao.getText());
        projeto.setTempoImpressao(Double.parseDouble(txtTempo.getText()));
        projeto.setQuantidadeMaterial(Double.parseDouble(txtMaterial.getText()));

        // CALCULARCUSTO OBJETO
        double materialReal = projeto.getQuantidadeMaterial() * 1.10;
        double custoMaterial = materialReal * material.getC_grama();
        double energiaKwh = (impressora.getPotencia() / 1000) * projeto.getTempoImpressao();
        double custoEnergia = energiaKwh * 0.60;
        double custoMaquina = (impressora.getPreco() / (2 * 365 * 8)) * projeto.getTempoImpressao();
        double custoMaoObra = projeto.getTempoImpressao() * 4;
        double manutencao = 2.50;
        double total = custoMaterial + custoEnergia + custoMaquina + custoMaoObra + manutencao;
        double venda = total * 1.30;

        // RESULTADO
        txtResultado.setText(
            "Projeto: "
                + projeto.getNomeArquivo()
                + "\nImpressora: "+ impressora.getModelo()
                + "\nMaterial: "+ material.getMaterial()
                + "\nQuantidade: "+ projeto.getQuantidadeMaterial()+ " g"
                + "\nTempo: "+ projeto.getTempoImpressao()+ " h"
                + "\n\nCusto Material: R$ "+ String.format("%.2f",custoMaterial)
                + "\nCusto Energia: R$ "+ String.format("%.2f",custoEnergia)
                + "\nCusto Máquina: R$ "+ String.format("%.2f",custoMaquina)
                + "\nMão de Obra: R$ "+ String.format("%.2f",custoMaoObra)
                + "\nManutenção: R$ "+ String.format("%.2f",manutencao)
                + "\n\nTOTAL: R$ "+ String.format("%.2f",total)
                + "\nVenda sugerida: R$ " + String.format("%.2f", venda)
            );
        });

        // Organização da tabela
        grid.add(lblModelo, 0, 0);
        grid.add(comboModelo, 1, 0);

        grid.add(lblArquivo, 0, 1);
        grid.add(txtArquivo, 1, 1);

        grid.add(lblDescricao, 0, 2);
        grid.add(txtDescricao, 1, 2);

        grid.add(lblTempo, 0, 3);
        grid.add(txtTempo, 1, 3);

        grid.add(lblQuantidade, 0, 4);
        grid.add(txtMaterial, 1, 4);

        grid.add(lblTipoMaterial, 0, 5);
        grid.add(comboMaterial, 1, 5);

        grid.add(btnCalcular, 0, 6);

        // Box da Imagem
        VBox painelDireito = new VBox(10);

        painelDireito.setAlignment(Pos.TOP_CENTER);

        painelDireito.getChildren().addAll(
            imagem,
            txtResultado
        );

        root.setLeft(grid);
        root.setRight(painelDireito);
        root.setTop(titulo);

        Scene scene = new Scene(root, 950, 550);

        comboModelo.setPrefWidth(250);
        comboMaterial.setPrefWidth(250);
        txtArquivo.setPrefWidth(250);
        txtDescricao.setPrefWidth(250);
        txtTempo.setPrefWidth(250);
        txtMaterial.setPrefWidth(250);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
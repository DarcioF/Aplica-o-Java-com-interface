package cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class Cliente{
    private String cpf;
    private Integer id;
    private String nome;
    private String email;
    private String telefone;

    public Integer getId() {
        return id;   }
    public void setId(Integer id) {
        this.id = id; }
    public String getNome() {
        return nome;  }
    public void setNome(String nome) {
        this.nome = nome; }
    public String getCpf(){
        return cpf; }
    public void setCpf(String cpf){
        this.cpf=cpf; }
    public String getEmail() {
        return email;}
    public void setEmail(String email) {
        this.email = email; }
    public String getTelefone() {
        return telefone; }
    public void setTelefone(String telefone) {
        this.telefone = telefone; }

    
   public void inserir(Cliente cliente){
       java.sql.Connection con= new Conexao().getConnection();
    
       String sql="INSERT INTO dados(nome,cpf,telefone,email)"
               + " values (?,?,?,?)";
       try {
           PreparedStatement stmt= con.prepareStatement(sql);
           stmt.setString(1, cliente.getNome());
           stmt.setString(2, cliente.getCpf());
           stmt.setString(3, cliente.getTelefone());
           stmt.setString(4, cliente.getEmail());
          
           stmt.execute();
           stmt.close();
          JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso");
           
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"ERRO"+e);
       }
   
   }
    
   public List<Cliente> getListar() throws SQLException{
   try{
       java.sql.Connection con= new Conexao().getConnection();
       List<Cliente> cliente = new ArrayList<Cliente>();
       PreparedStatement stmt = con.prepareStatement("select * from dados");
       ResultSet rs = stmt.executeQuery();
       
       while(rs.next()){
       Cliente cli= new Cliente();
       cli.setId(rs.getInt("id"));
       cli.setNome(rs.getString("nome"));
       cli.setCpf(rs.getString("cpf"));
       cli.setEmail(rs.getString("email"));
       cli.setTelefone(rs.getString("telefone"));
       
       cliente.add(cli);
   }
       rs.close();
       stmt.close();
       return cliente;
   }   catch (SQLException e){
       throw new RuntimeException(e);  
   }
   }
public List<Cliente> getBuscar(String des) throws SQLException{
   try{
       java.sql.Connection con= new Conexao().getConnection();
       List<Cliente> cliente = new ArrayList<Cliente>();
       PreparedStatement stmt = con.prepareStatement("select * from dados where nome LIKE ?");
       stmt.setString(1,"%"+des+"%");
       ResultSet rs = stmt.executeQuery();
       
       while(rs.next()){
       Cliente cli= new Cliente();
       cli.setId(rs.getInt("id"));
       cli.setNome(rs.getString("nome"));
       cli.setCpf(rs.getString("cpf"));
       cli.setEmail(rs.getString("email"));
       cli.setTelefone(rs.getString("telefone"));
       
       cliente.add(cli);
   }
       rs.close();
       stmt.close();
       return cliente;
   }   catch (SQLException e){
       throw new RuntimeException(e);  
   }
   }
      public List<Cliente> getBuscarCPF(String des) throws SQLException{
   try{
       java.sql.Connection con= new Conexao().getConnection();
       List<Cliente> cliente = new ArrayList<Cliente>();
       PreparedStatement stmt = con.prepareStatement("select * from dados where cpf LIKE ?");
       stmt.setString(1,"%"+des);
       ResultSet rs = stmt.executeQuery();
       
       while(rs.next()){
       Cliente cli= new Cliente();
       cli.setId(rs.getInt("id"));
       cli.setNome(rs.getString("nome"));
       cli.setCpf(rs.getString("cpf"));
       cli.setEmail(rs.getString("email"));
       cli.setTelefone(rs.getString("telefone"));
       
       cliente.add(cli);
   }
       rs.close();
       stmt.close();
       return cliente;
   }   catch (SQLException e){
       throw new RuntimeException(e);  
   }
   }
   /*public void imprimirCliente() throws SQLException{
       System.out.println("///////Lista de clientes///////");
       System.out.println("");
       Cliente cliente= new Cliente();
       List<Cliente> cli = cliente.getListar();
       
       cliente.getListar();
       for(Cliente cliented : cli){
           System.out.println("Nome: "+cliented.getNome());
           System.out.println("Endereço: "+cliented.getEndereco());
           System.out.println("Email: "+cliented.getEmail()); 
           System.out.println("Telefone: "+cliented.getTelefone());
           System.out.println("Celular: "+cliented.getCelular());
           System.out.println("Convenio: "+cliented.getConvenio());
           System.out.println("Codigo: "+cliented.getCod_cliente());
       
       }
   }
   */
   
  public void alterar(Cliente cliente, Integer id){
  java.sql.Connection con = new Conexao().getConnection();
  String sql = "update dados set nome=?,cpf=?,telefone=?,email=?"
          +" where id="+id;
  try{
      PreparedStatement stmt = con.prepareCall(sql);
      stmt.setString(1, cliente.getNome());
      stmt.setString(2, cliente.getCpf());
      stmt.setString(3, cliente.getTelefone());
      stmt.setString(4, cliente.getEmail());
      
      
      stmt.execute();
      stmt.close();
      JOptionPane.showMessageDialog(null,"Alterado com sucesso");
  }catch (SQLException e){
      JOptionPane.showMessageDialog(null,"Erro"+e);
  }
  }
  
  public void deletar(int id){
  java.sql.Connection con = new Conexao().getConnection();
  String sql = "delete from dados"
          +" where id="+id;
  try{
      PreparedStatement stmt = con.prepareCall(sql);
      stmt.execute();
      stmt.close();
      JOptionPane.showMessageDialog(null,"Removido com sucesso");
  }catch (SQLException e){
  JOptionPane.showMessageDialog(null,"Erro "+e);
  }
  
  }
  public boolean BuscarCPF(Cliente des){
          boolean ext=false;
          try{
       java.sql.Connection con= new Conexao().getConnection();
   
       PreparedStatement stmt = con.prepareStatement("select * from dados where cpf=?");
       stmt.setString(1, des.getCpf());
       ResultSet rs = stmt.executeQuery();
       
       if(rs.next()){ 
           rs.close();
           stmt.close();
           return ext=true;
   }
       rs.close();
       stmt.close();
      return ext;
   }   catch (SQLException e){
       throw new RuntimeException(e);  
   }
   }
 
 }



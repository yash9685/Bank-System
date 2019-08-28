package logitech; 
public class MainController{
  public void LoginPage(ActionEvent event) {
	
		if (fname.getText().isEmpty()) {
			warn1.setText(" * Please Enter the Name * ");
		}
		if (fid.getText().isEmpty()) {
			warn2.setText(" * Please enter Login id * ");
		}
		if (fpass.getText().isEmpty()) {
			warn3.setText(" * Please enter the password * ");
		}
		if (!fname.getText().isEmpty() && !fid.getText().isEmpty() && !fpass.getText().isEmpty()) {
			
			
			
			 try {
				
				Connection conn = DriverManager.getConnection(dburl, dbname, dbpass);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select Cpage.cloginid, Tpage.tpass from Cpage, Tpage where Cpage.s_no=Tpage.s_no");
				int flag=0;
				while(rs.next()) {
					if(rs.getString("cloginid").equals(fid.getText().toString()) && rs.getString("tpass").equals(fpass.getText().toString())) {
						System.out.println("Login Successful!");
						flag++;
					}
					
				}
				if(flag == 1){
					try {
//				 BorderPane root = new BorderPane();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/logitech/Spage.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch (Exception e) {
				System.out.println("Error in opening first page... ");
			}
				}else {
						System.out.println("Password entered is wrong");
						
					}
				
			 } catch (SQLException e1) {
				System.out.println("Connection problem! ");
			}
			 /*
				stmt.execute("insert into Fpage(fname, fid, fpass) values('"+fname.getText().toString()+"', '"+fid.getText().toString()+"', '"+fpass.getText().toString()+"')");
				System.out.println("Completed Task! Welcome to Virtual Bank");
			*/
			
			
			

		}
		
	}

	public void CreateAccount(ActionEvent event)  {

		try {
			// BorderPane root = new BorderPane();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/logitech/Cpage.fxml"));
			Scene scene = new Scene(root, 900, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("Error in Creating Account... ");
		}
		
		
	}

	public void BackAfterCreating(ActionEvent event) {

		try {
			// BorderPane root = new BorderPane();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/logitech/Fpage.fxml"));
			Scene scene = new Scene(root, 800, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("Error in Creating Account... ");
		}

	}

	public void NextAfterCreating(ActionEvent event) {

		if (cname.getText().isEmpty() || cphone.getText().isEmpty() || caddress.getText().isEmpty()
				|| ccity.getText().isEmpty() || cpin.getText().isEmpty() || cemail.getText().isEmpty()
				|| cfather.getText().isEmpty() || coccupation.getText().isEmpty()) {

			cwarnlabel.setText(" * Please fill every details * ");
		} else if (!cname.getText().isEmpty() || !cphone.getText().isEmpty() || !caddress.getText().isEmpty()
				|| !ccity.getText().isEmpty() || !cpin.getText().isEmpty() || !cemail.getText().isEmpty()
				|| !cfather.getText().isEmpty() || !coccupation.getText().isEmpty()) {

			try {
				Connection conn = DriverManager.getConnection(dburl, dbname, dbpass);
				Statement stmt = conn.createStatement();
				stmt.execute("insert into Cpage(cname, cphone, caddress, ccity, cpin, cemail, cdob, cfather, coccupation, cloginid) values('"+cname.getText().toString()+"', '"+cphone.getText().toString()+"', '"+caddress.getText().toString()+"', '"+ccity.getText().toString()+"', '"+cpin.getText().toString()+"', '"+cemail.getText().toString()+"', '"+ cdob.getValue().toString()+"', '"+cfather.getText().toString()+"', '"+coccupation.getText().toString()+"', '"+cloginid.getText().toString()+"')");
				System.out.println("Comepleted Task! You have successfully created a account ");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Found error: Check Next in Create Method Database handle. ");
				e1.printStackTrace();
				
			}
			
			
			try {
				// BorderPane root = new BorderPane();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/logitech/Tpage.fxml"));
				Scene scene = new Scene(root, 700, 600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch (Exception e) {
				System.out.println("Error in next of Creating Account... ");
			}
		}

	}

	public void AccountNumber(ActionEvent event) {

		// Generating account number

		String result = new SecureRandom().ints(0, 36).mapToObj(i -> Integer.toString(i, 36)).map(String::toUpperCase)
				.distinct().limit(16).collect(Collectors.joining()).replaceAll("([A-Z0-9]{4})", "$1-").substring(0, 19);
		taccountnumber.setText(result);

		// Generating pin
		Random rand = new Random();
		int mynum = rand.nextInt(9999-1000)+1000;
		tpin.setText(Integer.toString(mynum));
		
		tquestion.setItems(FXCollections.observableArrayList(
		    "What is your Pet Name?", "Your favorite sports?", 
		    new Separator(), "Which book would you like to read?", "Which habbit makes you better?")
		);
		

	}
	
	public void TNextButton(ActionEvent event) {
		
		try {
			Connection conn = DriverManager.getConnection(dburl, dbname, dbpass);
			Statement stmt = conn.createStatement();
			stmt.execute("insert into Tpage(taccountnumber, tpin, tquestion, tanswer, tpass) values('"+taccountnumber.getText().toString()+"', '"+tpin.getText().toString()+"', '"+tquestion.getValue().toString()+"', '"+tanswer.getText().toString()+"', '"+tpass.getText().toString()+"')");
			System.out.println("Completed! You have generated your Account Number and the login password. Keep it secure and Dont share among others! ");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Hangin between db and the submit account number process.");
		}

		try {
			// BorderPane root = new BorderPane();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/logitech/Spage.fxml"));
			Scene scene = new Scene(root, 800, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("Error in Creating Account... ");
		}

	}

}


/*
 * 
 * End of the file.
 *  
 */

package com.example.jsonparsing;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactView extends LinearLayout {

    public ContactView(Context context) {
        super(context);
        initialContactView(context);
    }
    public ContactView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        initialContactView(context);
    }
    public ContactView(Context context, AttributeSet attributeSet,int deffStyleAtr)
    {
        super(context,attributeSet,deffStyleAtr);
        initialContactView(context);
    }
    private void initialContactView(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.contact_view, this);
        nameTextView = findViewById(R.id.nameCon);
        emailTextView = findViewById(R.id.email);
        imageView = findViewById(R.id.photo);
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        emailTextView.setText(email);
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setContactName(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        nameTextView.setText(firstName+" "+lastName);
    }
    public int getIdContact() {
        return idContact;
    }
    public void setIdContact(int id) {
        this.idContact = id;
    }
    public void setImage(String urlImage)
    {
        DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);
        downloadImageTask.execute(urlImage);
    }

    private String email;
    private String firstName;
    private String lastName;
    private int idContact;

    private ImageView imageView;
    private TextView nameTextView;
    private TextView emailTextView;

}

    public void selectFile ()
    {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i,"Select a file"), CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String filePath = data.getDataString();
        Uri SelectedFileLocation=Uri.parse(filePath);
        UploadFile(SelectedFileLocation);
        super.onActivityResult(requestCode, resultCode, data);
    }
    public  void UploadFile(Uri file)
    {
        Toast.makeText(this, "Please wait.. the file is uploading!", Toast.LENGTH_SHORT).show();
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        StorageReference riversRef = mStorageRef.child(file.getLastPathSegment());
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UploadActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(UploadActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

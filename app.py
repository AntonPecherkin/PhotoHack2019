from flask import Flask, jsonify 
from flask import request, render_template, redirect, url_for
import requests 
import os
from werkzeug.utils import secure_filename
UPLOAD_FOLDER = 'static/uploads'

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


@app.route('/')
def hello_world():
    files = {
    'image_url[1]': (None, 'http://livedemo.su:5000/static/man.jpg'),
    'rotate[1]': (None, '0'),
    'flip[1]': (None, '0'),
    'crop[1]': (None, '0,0,1,1'),
    'template_name': (None, '1986'),
    }
    response = requests.post('http://api-soft.photolab.me/template_process.php', files=files)
    resp_body = response.text
    return resp_body

@app.route("/upload", methods=['POST'])
def uploadfile():
    file = request.files['file']
    basedir = os.path.abspath(os.path.dirname(__file__))
    if file:
        filename = secure_filename(file.filename) + ".jpg"
        file.save(os.path.join(basedir, app.config['UPLOAD_FOLDER'], filename))
    files = {
    'image_url[1]': (None, 'http://livedemo.su:5000/static/uploads/' + filename),
    'rotate[1]': (None, '0'),
    'flip[1]': (None, '0'),
    'crop[1]': (None, '0,0,1,1'),
    'template_name': (None, '682'),
    }
    response = requests.post('http://api-soft.photolab.me/template_process.php', files=files)
    resp_body1 = response.text
    
    files = {
    'image_url[1]': (None, 'http://livedemo.su:5000/static/uploads/' + filename),
    'rotate[1]': (None, '0'),
    'flip[1]': (None, '0'),
    'crop[1]': (None, '0,0,1,1'),
    'template_name': (None, '2960'),
    }
    response = requests.post('http://api-soft.photolab.me/template_process.php', files=files)
    resp_body2 = response.text

    files = {
    'image_url[1]': (None, 'http://livedemo.su:5000/static/uploads/' + filename),   
    'rotate[1]': (None, '0'),
    'flip[1]': (None, '0'),
    'crop[1]': (None, '0,0,1,1'),
    'template_name': (None, '716'),
    }
    response = requests.post('http://api-soft.photolab.me/template_process.php', files=files)
    resp_body3 = response.text

    files = {
    'image_url[1]': (None, 'http://livedemo.su:5000/static/uploads/' + filename),   
    'rotate[1]': (None, '0'),
    'flip[1]': (None, '0'),
    'crop[1]': (None, '0,0,1,1'),
    'template_name': (None, '1994'),
    }
    response = requests.post('http://api-soft.photolab.me/template_process.php', files=files)
    resp_body4 = response.text
    
    files = {
    'image_url[1]': (None, 'http://livedemo.su:5000/static/uploads/' + filename),
    'rotate[1]': (None, '0'),
    'flip[1]': (None, '0'),
    'crop[1]': (None, '0,0,1,1'),
    'template_name': (None, '1994'),
    }
    response = requests.post('http://api-soft.photolab.me/template_process.php', files=files)
    resp_body5 = response.text 
    
    json_data = []
    json_data.append({'url1': resp_body1, 'url2': resp_body2, 'url3': resp_body3, 'url4': resp_body4})
    #return jsonify(json_data)
    return resp_body1 + ',' + resp_body2 + ',' + resp_body3 + ',' + resp_body4 + ',' + resp_body5

if __name__ == '__main__':
    app.run(debug=True, host='livedemo.su')


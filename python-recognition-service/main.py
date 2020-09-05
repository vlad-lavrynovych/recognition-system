from flask import Flask, request, send_file
import io
import numpy as np

from nn import nn
from setting import environment, constant
import argparse
import processor

app = Flask(__name__)


@app.route("/", methods=["POST"])
def perform_recognition():
    file = request.files["file"]
    buf = file.read()

    return send_file(
        io.BytesIO(nn.testBytes(buf)),
        attachment_filename='image.jpeg',
        mimetype='image/jpg'
    )

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--workers',
                        help='Number of multiprocessing workers. To disable multiprocessing, set workers to 0',
                        type=int, default=0)
    parser.add_argument("--tolabel", help="Preprocess images to create labels (out/tolabel)", action="store_true",
                        default=False)
    parser.add_argument("--augmentation", help="Dataset augmentation (pass quantity)", type=int)
    parser.add_argument("--dataset", help="Dataset name", type=str, default=constant.DATASET)
    parser.add_argument("--train", help="Train", action="store_true", default=False)
    parser.add_argument("--test", help="Predict", action="store_true", default=True)
    parser.add_argument("--arch", help="Neural Network architecture", type=str, default=constant.MODEL)
    parser.add_argument("--dip", help="Method for image processing", type=str, default=constant.IMG_PROCESSING)
    parser.add_argument("--gpu", help="Enable GPU mode", action="store_true", default=True)
    args = parser.parse_args()
    environment.setup(args)

    app.run(host='localhost', port=3003, debug=True)
